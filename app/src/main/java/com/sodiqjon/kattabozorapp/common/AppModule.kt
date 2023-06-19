package com.sodiqjon.kattabozorapp.common

import android.content.Context
import com.sodiqjon.kattabozorapp.api.ServiceApi
import com.sodiqjon.kattabozorapp.repository.Repository
import com.sodiqjon.kattabozorapp.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun getOkHttp(@ApplicationContext context: Context): OkHttpClient = try {
        val builder = OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
        builder.build()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }

    @Singleton
    @Provides
    fun getServiceApi(client: OkHttpClient): ServiceApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun getRepository(
        serviceApi: ServiceApi
    ): Repository = RepositoryImpl(serviceApi)
}