package gka.android.limehd.data.remote

import retrofit2.Response

sealed class Resource<T> {
    class Success<T>(val data: T?) : Resource<T>()
    class Error<T>(val errorCode: Int, val errorMessage: String?) : Resource<T>()
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