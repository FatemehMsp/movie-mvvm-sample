package com.github.fatemehmsp.movie.di.EpisodeDetail

import androidx.lifecycle.ViewModel
import com.github.fatemehmsp.movie.di.ViewModelKey
import com.github.fatemehmsp.movie.viewmodel.EpisodeDetailViewModel
import dagger.Binds
import dagger.multibindings.IntoMap

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */
abstract class EpisodeDetailViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EpisodeDetailViewModel::class)
    abstract fun bindEpisodeDetailViewModel(episodeDetailViewModel: EpisodeDetailViewModel) : ViewModel
}