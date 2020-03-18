package com.github.fatemehmsp.movie.di.EpisodeList

import com.github.fatemehmsp.movie.di.ApplicationComponent
import com.github.fatemehmsp.movie.ui.activity.EpisodeListActivity
import com.github.fatemehmsp.movie.viewmodel.ViewModelFactory
import dagger.Component

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */
@EpisodeListActivityScope
@Component(dependencies = [ApplicationComponent::class]
    ,modules = [EpisodeListViewModelModule::class])
interface EpisodeListActivityComponent {

    fun viewModelFactory() : ViewModelFactory
    fun inject(episodeListActivity: EpisodeListActivity)

    @Component.Builder
    interface Builder{

        fun applicationComponent(applicationComponent: ApplicationComponent):Builder

        fun build() : EpisodeListActivityComponent
    }
}