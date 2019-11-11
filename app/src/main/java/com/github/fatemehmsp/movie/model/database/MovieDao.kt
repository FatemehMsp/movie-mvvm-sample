package com.github.fatemehmsp.movie.model.database

import androidx.room.Dao
import androidx.room.Query
import com.github.fatemehmsp.movie.model.MovieModel

/**
 * Created by Fatemeh Movassaghpour on 10/19/2019.
 */
@Dao
interface MovieDao : BaseDao<MovieModel> {

    @Query("select * from movie where title=:title")
    fun getMovieByTitle(title: String): MovieModel?

    @Query("delete from movie where title=:title")
    fun deleteMovieByTitle(title: String)
}