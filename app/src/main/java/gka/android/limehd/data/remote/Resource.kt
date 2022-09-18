package gka.android.limehd.data.remote

import retrofit2.Response

sealed class Resource<T>(
    val data: T? = null,
    val errorCode: Int? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Error<T>(errorCode: Int, errorMessage: String?) :
        Resource<T>(errorCode = errorCode, errorMessage = errorMessage)
}


fun <T> checkResponse(response: Response<T>): Resource<T> {
    return if (response.isSuccessful) {
        response.body()?.let {
            return Resource.Success(it)
        }
        Resource.Success(response.body())
    } else {
        val errorMessage: String? = try {
            response.errorBody()?.string()
        } catch (e: Exception) {
            null
        }
        Resource.Error(response.code(), errorMessage)
    }
}