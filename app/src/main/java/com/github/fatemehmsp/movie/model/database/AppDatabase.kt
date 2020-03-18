package com.github.fatemehmsp.movie.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.fatemehmsp.movie.App
import com.github.fatemehmsp.movie.model.EpisodeModel
import com.github.fatemehmsp.movie.model.MovieModel


/**
 * Created by Fatemeh Movassaghpour on 10/19/2019.
 */

@Database(entities = [MovieModel::class, EpisodeModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun episodeDao(): EpisodeDao

    companion object {
        const val DB_NAME = "movie_db"
    }



}