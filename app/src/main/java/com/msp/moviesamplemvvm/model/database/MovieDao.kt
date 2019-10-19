package com.msp.moviesamplemvvm.model.database

import androidx.room.Dao
import androidx.room.Query
import com.msp.moviesamplemvvm.model.MovieModel
import io.reactivex.Single

/**
 * Created by Fatemeh Movassaghpour on 10/19/2019.
 */
@Dao
interface MovieDao : BaseDao<MovieModel> {

    @Query("select * from movie where title=:title")
    fun getMovieByTitle(title: String): Single<MovieModel>

    @Query("delete from movie where title=:title")
    fun deleteMovieByTitle(title: String)
}