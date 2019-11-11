package com.github.fatemehmsp.movie

import android.app.Application
import android.content.Context
import io.reactivex.plugins.RxJavaPlugins


class App : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { throwable -> } // nothing or some logging
    }

}