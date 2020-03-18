package com.github.fatemehmsp.movie.Api

import com.github.fatemehmsp.movie.data.model.MovieModel
import com.github.fatemehmsp.movie.data.model.SeasonResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    private val service: ApiService

    init {
        service = getClient(BASE_URL).create(
            ApiService::class.java
        )
    }

    private fun getClient(baseUrl: String): Retrofit {
        //create okHttp client
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    fun getMovieData(): Single<MovieModel> {
        return service.getMovie(
            API_KEY,
            MOVIE_TITLE
        )
    }

    fun getSeason(season: Int): Single<SeasonResponse> {
        return service.getSeason(
            API_KEY,
            MOVIE_TITLE, season
        )
    }

    fun getEpisode(episode: String, season: Int): Single<MovieModel> {
        return service.getEpisode(
            API_KEY,
            MOVIE_TITLE, episode, season
        )
    }

    companion object {
        val BASE_URL = "http://www.omdbapi.com"

        // timeouts
        val CONNECT_TIMEOUT: Long = 20
        val READ_TIMEOUT: Long = 40
        val WRITE_TIMEOUT: Long = 120

        val API_KEY = "f6defde"
        val MOVIE_TITLE = "Game of Thrones"
    }

}