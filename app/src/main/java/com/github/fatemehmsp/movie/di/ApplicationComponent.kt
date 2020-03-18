package com.github.fatemehmsp.movie.di

import com.github.fatemehmsp.movie.Api.ApiService
import com.github.fatemehmsp.movie.App
import com.github.fatemehmsp.movie.data.database.AppDatabase
import com.github.fatemehmsp.movie.di.MainActivity.MainActivityComponent
import com.github.fatemehmsp.movie.di.module.DatabaseModule
import com.github.fatemehmsp.movie.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */
@Singleton
@Component(modules = [NetworkModule::class,
                        DatabaseModule::class])
interface ApplicationComponent {

    fun apiService():ApiService
    fun appDatabase():AppDatabase

    @Component.Builder
    interface Builder{

       @BindsInstance
        fun application(app: App) : Builder

        fun Build() : ApplicationComponent
    }
}