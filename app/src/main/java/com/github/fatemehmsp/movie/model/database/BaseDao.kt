package com.github.fatemehmsp.movie.model.database

import androidx.room.*

/**
 * Created by Fatemeh Movassaghpour on 10/19/2019.
 */
@Dao
interface BaseDao<in T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T)

    @Delete
    fun delete(type: T)

    @Update
    fun update(type: T)

}
