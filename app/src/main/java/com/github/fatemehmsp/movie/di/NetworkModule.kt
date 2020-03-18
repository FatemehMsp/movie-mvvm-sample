package com.github.fatemehmsp.movie.di

import com.github.fatemehmsp.movie.App
import com.github.fatemehmsp.movie.network.ApiClient
import com.github.fatemehmsp.movie.network.ApiService
import com.github.fatemehmsp.movie.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory,
                        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
                        okHttpClient: OkHttpClient) :Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory() : GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRxJava2CallAdapterFactory() : RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.create()

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient {
        //create okHttp client
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(ApiClient.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(ApiClient.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(ApiClient.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit):ApiService =
        retrofit.create(ApiService::class.java)

}