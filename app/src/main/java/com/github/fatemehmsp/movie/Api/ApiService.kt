package com.github.fatemehmsp.movie.Api

import com.github.fatemehmsp.movie.data.model.MovieModel
import com.github.fatemehmsp.movie.data.model.SeasonResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("/?f6defde,")
    fun getMovie(
        @Query("t") title: String
    ): Single<MovieModel>


    @GET("/?f6defde,")
    fun getSeason(
        @Query("t") title: String,
        @Query("Season") Season: Int
    ): Single<SeasonResponse>

    @GET("/?f6defde,")
    fun getEpisode(
        @Query("t") title: String,
        @Query("Episode") episode: String,
        @Query("Season") season: Int
    ): Single<MovieModel>

}