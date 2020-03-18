package com.github.fatemehmsp.movie.di

import com.github.fatemehmsp.movie.App
import com.github.fatemehmsp.movie.di.module.DatabaseModule
import com.github.fatemehmsp.movie.di.module.NetworkModule
import com.github.fatemehmsp.movie.model.database.AppDatabase
import com.github.fatemehmsp.movie.network.ApiService
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


    @Component.Builder
    interface Builder{

       @BindsInstance
        fun application(app: App) : Builder

        fun Build() : ApplicationComponent
    }
}