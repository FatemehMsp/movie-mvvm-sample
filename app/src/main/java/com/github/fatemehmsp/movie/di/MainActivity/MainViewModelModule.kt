package com.github.fatemehmsp.movie.di.MainActivity

import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import com.github.fatemehmsp.movie.Api.ApiService
import com.github.fatemehmsp.movie.data.database.AppDatabase
import com.github.fatemehmsp.movie.di.ViewModelKey
import com.github.fatemehmsp.movie.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */
@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
   abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

}