package com.github.fatemehmsp.movie.data.repository

import com.github.fatemehmsp.movie.Api.ApiService
import com.github.fatemehmsp.movie.Api.Resource
import com.github.fatemehmsp.movie.data.database.MovieDao
import com.github.fatemehmsp.movie.data.model.MovieModel
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Fatemeh Movassaghpour on 3/22/2020.
 */
@Singleton
class MovieRepository@Inject constructor(
    private val movieDao: MovieDao
    ,private val apiService: ApiService)
{

    fun getMovie(title:String):Single<MovieModel> {
      return apiService.getMovie().flatMap { movie ->
          movie.title?.let {
              movieDao.deleteMovieByTitle(it)
              movieDao.insert(movie)
              Single.just(movie)
          } ?: movieDao.getMovieByTitle(title)
      }.onErrorReturn {
          movieDao.getMovieByTitle(title).blockingGet()
      }
    }

    fun getEpisode(episode:String,seasonId:Int,episodeTitle: String) : Single<MovieModel> {
        return apiService.getEpisode(episode, seasonId).flatMap { movie ->
            movie.title?.let {
                movieDao.deleteMovieByTitle(it)
                movieDao.insert(movie)
                Single.just(movie)
            }
        }.onErrorReturn {
            movieDao.getMovieByTitle(episodeTitle).blockingGet()
        }
    }
}

