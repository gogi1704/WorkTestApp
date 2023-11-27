package com.l_george.worktestapp.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    companion object {
        private const val BASE_URL = "https://easypay.world/api-test/"
    }

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("app-key" , "12345")
                    .addHeader("v" , "1")
                    .build()
                return@addInterceptor chain.proceed(request)
            }
            .callTimeout(30, TimeUnit.SECONDS)
            .build()


    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


}