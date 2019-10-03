package com.msp.moviesamplemvvm.network

import com.msp.moviesamplemvvm.model.MovieModel
import com.msp.moviesamplemvvm.model.SeasonResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("/?")
    fun getMovie(
        @Query("apikey") apikey: String,
        @Query("t") title: String
    ): Single<MovieModel>


    @GET("/?")
    fun getSeason(
        @Query("apikey") apikey: String,
        @Query("t") title: String,
        @Query("Season") Season: Int
    ): Single<SeasonResponse>

    @GET("/?")
    fun getEpisode(
        @Query("apikey") apikey: String,
        @Query("t") title: String,
        @Query("Episode") episode: Int
    ): Single<MovieModel>

}