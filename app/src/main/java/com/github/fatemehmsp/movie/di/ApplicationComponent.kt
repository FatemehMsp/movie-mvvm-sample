package com.github.fatemehmsp.movie.di

import com.github.fatemehmsp.movie.App
import com.github.fatemehmsp.movie.di.module.DatabaseModule
import com.github.fatemehmsp.movie.di.module.NetworkModule
import com.github.fatemehmsp.movie.di.module.ViewModelFactoryModule
import com.github.fatemehmsp.movie.viewmodel.ViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */
@Singleton
@Component(modules = [ViewModelFactoryModule::class,
    NetworkModule::class,
    DatabaseModule::class])
interface ApplicationComponent {

    fun viewModelFactory() : ViewModelFactory

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(app: App) : Builder

        fun Build() : ApplicationComponent
    }
}