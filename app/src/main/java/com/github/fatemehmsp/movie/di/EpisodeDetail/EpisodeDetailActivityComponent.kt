package com.github.fatemehmsp.movie.di.EpisodeDetail

import com.github.fatemehmsp.movie.di.ApplicationComponent
import com.github.fatemehmsp.movie.di.module.ViewModelFactoryModule
import com.github.fatemehmsp.movie.ui.activity.EpisodeDetailActivity
import com.github.fatemehmsp.movie.ui.activity.EpisodeListActivity
import com.github.fatemehmsp.movie.viewmodel.ViewModelFactory
import dagger.BindsInstance
import dagger.Component

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */

@EpisodeDetailActivityScope
@Component(dependencies = [ApplicationComponent::class]
    ,modules = [EpisodeDetailViewModelModule::class])
interface EpisodeDetailActivityComponent {

    fun viewModelFactory() : ViewModelFactory
    fun inject(episodeDetailActivity: EpisodeDetailActivity)

    @Component.Builder
    interface Builder{

        fun applicationComponent(applicationComponent: ApplicationComponent):Builder

        fun build() : EpisodeDetailActivityComponent
    }
}