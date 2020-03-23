package com.github.fatemehmsp.movie.Api

import com.github.fatemehmsp.movie.data.model.MovieModel
import com.github.fatemehmsp.movie.data.model.SeasonResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("/?apikey=f6defde&t=Game of Thrones&")
    fun getMovie(): Single<MovieModel>


    @GET("/?apikey=f6defde&t=Game of Thrones&")
    fun getSeason(
        @Query("Season") Season: Int
    ): Single<SeasonResponse>

    @GET("/?apikey=f6defde&t=Game of Thrones&")
    fun getEpisode(
        @Query("Episode") episode: String,
        @Query("Season") season: Int
    ): Single<MovieModel>

}