package com.github.fatemehmsp.movie.di.module

import androidx.lifecycle.ViewModelProvider
import com.github.fatemehmsp.movie.di.EpisodeDetail.EpisodeDetailViewModelModule
import com.github.fatemehmsp.movie.di.EpisodeList.EpisodeListViewModelModule
import com.github.fatemehmsp.movie.di.MainActivity.MainViewModelModule
import com.github.fatemehmsp.movie.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */
@Module(includes = [MainViewModelModule::class ,
                    EpisodeDetailViewModelModule::class,
                    EpisodeListViewModelModule::class])
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}