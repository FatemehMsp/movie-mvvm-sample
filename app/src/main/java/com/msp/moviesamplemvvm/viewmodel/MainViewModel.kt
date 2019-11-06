package com.msp.moviesamplemvvm.viewmodel

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
    val loadingProgressBar: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }

    init {
        getMovieData()
    }

    private fun getMovieData() {
        loadingProgressBar.postValue(true)
        disposables.add(
            ApiClient().getMovieData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieModel>() {
                    override fun onSuccess(t: MovieModel) {
                        Log.d(TAG, "onSuccess")
                        loadingProgressBar.postValue(false)
                        movieData.postValue(t)
                        AppDatabase.getInstance().movieDao().insert(t)
                    }

                    override fun onError(e: Throwable) {
                        loadingProgressBar.postValue(false)
                        Log.e(TAG, "onError: " + e.message)
                    }
                }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}