package com.github.fatemehmsp.movie.di.MainActivity

import com.github.fatemehmsp.movie.ui.activity.MainActivity
import dagger.Component

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */
@MainActivityScope
@Component
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)


}