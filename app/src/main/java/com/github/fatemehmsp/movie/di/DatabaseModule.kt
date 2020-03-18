package com.github.fatemehmsp.movie.di

import androidx.room.Room
import com.github.fatemehmsp.movie.App
import com.github.fatemehmsp.movie.model.database.AppDatabase
import com.github.fatemehmsp.movie.model.database.EpisodeDao
import com.github.fatemehmsp.movie.model.database.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: App) : AppDatabase {
       return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase) : MovieDao =
        appDatabase.movieDao()

    @Provides
    fun provideEpisodeDao(appDatabase: AppDatabase) : EpisodeDao =
        appDatabase.episodeDao()
}