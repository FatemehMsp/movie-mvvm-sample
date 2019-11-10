package com.msp.moviesamplemvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Fatemeh Movassaghpour on 11/7/2019.
 */
class ViewModelFactory(private var param1: Int = 0, private var param2: String = "") :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(EpisodeListViewModel::class.java))
            EpisodeListViewModel(param1) as T
        else
            EpisodeDetailViewModel(param1, param2) as T
    }
}