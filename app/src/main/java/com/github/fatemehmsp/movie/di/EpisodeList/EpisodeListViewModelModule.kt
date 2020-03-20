package com.github.fatemehmsp.movie.di.EpisodeList

import androidx.lifecycle.ViewModel
import com.github.fatemehmsp.movie.Api.ApiService
import com.github.fatemehmsp.movie.data.database.AppDatabase
import com.github.fatemehmsp.movie.di.ViewModelKey
import com.github.fatemehmsp.movie.viewmodel.EpisodeListViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */
@Module
class EpisodeListViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(EpisodeListViewModel::class)
    fun provideEpisodeListViewModel(apiService: ApiService,appDatabase: AppDatabase) :ViewModel =
        EpisodeListViewModel(apiService,appDatabase)
}