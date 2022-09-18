package gka.android.limehd.di

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Binds
import dagger.Module
import dagger.Provides
import gka.android.limehd.data.DataRepository
import gka.android.limehd.data.DataRepositoryImpl
import gka.android.limehd.data.local.DatabaseDao
import gka.android.limehd.data.local.DatabaseRoom
import gka.android.limehd.data.remote.LimeHdApi
import gka.android.limehd.data.remote.RemoteDataRepository
import gka.android.limehd.data.remote.RemoteDataRepositoryImpl
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext


@Module(includes = [DataModuleBinds::class])
class DataModule {
    @Provides
    fun provideLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideLimeHdService(okHttpClient: OkHttpClient): LimeHdApi {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://limehd.online/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(LimeHdApi::class.java)
    }

    @Provides
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    fun provideContentDatabase(
        context: Context
    ): DatabaseRoom = DatabaseRoom.create(context)

    @Provides
    fun provideDatabaseDao(database: DatabaseRoom): DatabaseDao = database.databaseDao()

    @Provides
    fun providePicasso(context: Context): Picasso {
        return Picasso.Builder(context).build()
    }
}

@Module
interface DataModuleBinds {
    @Binds
    @Suppress("FunctionName")
    fun bindDataRepository_to_DataRepositoryImpl(dataRepositoryImpl: DataRepositoryImpl): DataRepository

    @Binds
    @Suppress("FunctionName")
    fun bindRemoteDataRepository_to_RemoteDataRepositoryImpl(remoteDataRepositoryImpl: RemoteDataRepositoryImpl): RemoteDataRepository

}
