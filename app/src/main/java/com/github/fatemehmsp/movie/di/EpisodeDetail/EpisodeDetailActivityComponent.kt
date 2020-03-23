package com.github.fatemehmsp.movie.di.EpisodeDetail

import com.github.fatemehmsp.movie.di.ApplicationComponent
import com.github.fatemehmsp.movie.ui.activity.EpisodeDetailActivity
import dagger.Component

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */

@EpisodeDetailActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface EpisodeDetailActivityComponent {

    fun inject(episodeDetailActivity: EpisodeDetailActivity)

    @Component.Builder
    interface Builder{

        fun applicationComponent(applicationComponent: ApplicationComponent):Builder

        fun build() : EpisodeDetailActivityComponent
    }
}