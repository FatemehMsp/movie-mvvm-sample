package com.github.fatemehmsp.movie.di.EpisodeList

import com.github.fatemehmsp.movie.di.ApplicationComponent
import com.github.fatemehmsp.movie.ui.activity.EpisodeListActivity
import dagger.Component

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */
@EpisodeListActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface EpisodeListActivityComponent {

    fun inject(episodeListActivity: EpisodeListActivity)

}