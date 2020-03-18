package com.github.fatemehmsp.movie.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.github.fatemehmsp.movie.data.model.MovieModel

/**
 * Created by Fatemeh Movassaghpour on 10/19/2019.
 */
@Dao
interface MovieDao : BaseDao<MovieModel> {

    @Query("select * from movie where title=:title")
    fun getMovieByTitle(title: String): LiveData<MovieModel>

    @Query("delete from movie where title=:title")
    fun deleteMovieByTitle(title: String)
}