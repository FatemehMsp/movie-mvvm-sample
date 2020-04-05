package com.github.fatemehmsp.movie

import android.app.Application
import android.content.Context
import com.github.fatemehmsp.movie.di.ApplicationComponent
import com.github.fatemehmsp.movie.di.DaggerApplicationComponent
import io.reactivex.plugins.RxJavaPlugins


class App : Application() {

   private lateinit var applicationComponent : ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { throwable -> } // nothing or some logging

        applicationComponent = DaggerApplicationComponent
            .builder()
            .application(this)
            .Build()
    }

    fun getApplicationComponent() = applicationComponent

}