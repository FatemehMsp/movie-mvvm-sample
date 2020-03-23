package com.github.fatemehmsp.movie.di.MainActivity

import com.github.fatemehmsp.movie.di.ApplicationComponent
import com.github.fatemehmsp.movie.ui.activity.MainActivity
import dagger.Component

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */
@MainActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder{

        fun applicationComponent(applicationComponent: ApplicationComponent):Builder

        fun build() : MainActivityComponent
    }

}