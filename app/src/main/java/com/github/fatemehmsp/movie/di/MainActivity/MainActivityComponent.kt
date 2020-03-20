package com.github.fatemehmsp.movie.di.MainActivity

import com.github.fatemehmsp.movie.di.ApplicationComponent
import com.github.fatemehmsp.movie.di.module.ViewModelFactoryModule
import com.github.fatemehmsp.movie.ui.activity.MainActivity
import com.github.fatemehmsp.movie.viewmodel.ViewModelFactory
import dagger.BindsInstance
import dagger.Component

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */
@MainActivityScope
@Component(dependencies = [ApplicationComponent::class]
    ,modules = [ViewModelFactoryModule::class])
interface MainActivityComponent {

    fun viewModelFactory() : ViewModelFactory
    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder{

        fun applicationComponent(applicationComponent: ApplicationComponent):Builder

        fun build() : MainActivityComponent
    }

}