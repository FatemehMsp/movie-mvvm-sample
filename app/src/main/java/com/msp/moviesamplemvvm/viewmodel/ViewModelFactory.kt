package com.msp.moviesamplemvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Fatemeh Movassaghpour on 11/7/2019.
 */
class ViewModelFactory(private val param: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelFactory(param) as T
    }
}