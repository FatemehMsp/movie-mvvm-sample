package com.msp.moviesamplemvvm.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msp.moviesamplemvvm.model.MovieModel
import com.msp.moviesamplemvvm.model.database.AppDatabase
import com.msp.moviesamplemvvm.network.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


/**
 * Created by Fatemeh Movassaghpour on 10/19/2019.
 */
class MainViewModel : ViewModel() {

    private val TAG: String = MainViewModel::class.java.simpleName
    val movieData: MutableLiveData<MovieModel> by lazy { MutableLiveData<MovieModel>() }
    private val disposables = CompositeDisposable()
    private var loadingProgressBar = false

    init {
        getMovieData()
    }

    private fun getMovieData() {
        loadingProgressBar = true
        disposables.add(
            ApiClient().getMovieData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieModel>() {
                    override fun onSuccess(t: MovieModel) {
                        Log.e(TAG, "onSuccess: ")
                        loadingProgressBar = false
                        movieData.postValue(t)
                        AppDatabase.getInstance().movieDao().insert(t)
                    }

                    override fun onError(e: Throwable) {
                        loadingProgressBar = true
                        Log.d(TAG, "onError: " + e.message)
                    }
                }
                )
        )
    }

    fun visibleLoading(): Boolean = loadingProgressBar

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}