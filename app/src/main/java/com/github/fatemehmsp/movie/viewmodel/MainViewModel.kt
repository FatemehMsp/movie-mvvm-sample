package com.github.fatemehmsp.movie.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.fatemehmsp.movie.R
import com.github.fatemehmsp.movie.model.MovieModel
import com.github.fatemehmsp.movie.model.SeasonModel
import com.github.fatemehmsp.movie.model.database.AppDatabase
import com.github.fatemehmsp.movie.network.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by Fatemeh Movassaghpour on 10/19/2019.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG: String = MainViewModel::class.java.simpleName
    val movieData: MutableLiveData<MovieModel> by lazy { MutableLiveData<MovieModel>() }
    private val disposables = CompositeDisposable()
    val loadingProgressBar: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }
    private val context = application.applicationContext
    val message: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    init {
        getMovieData()
    }

    private fun getMovieData() {
        loadingProgressBar.postValue(true)
        disposables.add(
            ApiClient().getMovieData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError)
        )
    }

    private fun onSuccess(movieModel: MovieModel) {
        Log.d(TAG, "onSuccess")
        message.postValue(R.string.message_online)
        loadingProgressBar.postValue(false)
        AppDatabase.getInstance().movieDao().deleteMovieByTitle(MOVIE_TITLE)
        AppDatabase.getInstance().movieDao().insert(movieModel)
        movieModel.seasonList = SeasonModel().getSeasonFromJson(context)
        movieData.postValue(movieModel)
    }

    private fun onError(e: Throwable) {
        Log.e(TAG, "onError: " + e.message)
        AppDatabase.getInstance().movieDao().getMovieByTitle(MOVIE_TITLE)?.let {
            message.postValue(R.string.message_offline)
            it.seasonList = SeasonModel().getSeasonFromJson(context)
            movieData.postValue(it)
            loadingProgressBar.postValue(false)
        } ?: notNetworkConnection()

    }

    private fun notNetworkConnection() {
        message.postValue(R.string.message_not_network_connection)
        loadingProgressBar.postValue(true)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    companion object {
        const val MOVIE_TITLE = "Game of Thrones"
    }
}