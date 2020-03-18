package com.github.fatemehmsp.movie.di.EpisodeList

import androidx.lifecycle.ViewModel
import com.github.fatemehmsp.movie.di.ViewModelKey
import com.github.fatemehmsp.movie.viewmodel.EpisodeListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */
@Module
abstract class EpisodeListModule {

    @Binds
    @IntoMap
    @ViewModelKey(EpisodeListViewModel::class)
    abstract fun bindEpisodeListViewModel(episodeListViewModel: EpisodeListViewModel) :ViewModel
}