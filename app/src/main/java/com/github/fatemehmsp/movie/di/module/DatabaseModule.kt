package com.github.fatemehmsp.movie.di.module

import androidx.room.Room
import com.github.fatemehmsp.movie.App
import com.github.fatemehmsp.movie.data.database.AppDatabase
import com.github.fatemehmsp.movie.data.database.EpisodeDao
import com.github.fatemehmsp.movie.data.database.MovieDao
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