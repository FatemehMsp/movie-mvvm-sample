package com.github.fatemehmsp.movie.di

import androidx.databinding.Bindable
import com.github.fatemehmsp.movie.App
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */
@Singleton
@Component(modules = [DatabaseModule::class,
                        NetworkModule::class])
interface ApplicationComponent {

    fun inject(app:App)

    @Component.Builder
    interface Builder{

       @BindsInstance
        fun application(app: App) : Builder

        fun Build() : ApplicationComponent
    }
}