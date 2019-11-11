package com.msp.moviesamplemvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msp.moviesamplemvvm.model.MovieModel
import com.msp.moviesamplemvvm.model.database.AppDatabase
import com.msp.moviesamplemvvm.network.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Fatemeh Movassaghpour on 11/10/2019.
 */
class EpisodeDetailViewModel(
    private val seasonId: Int,
    private val episode: String,
    private val episodeTitle: String
) : ViewModel() {

    private val TAG: String = EpisodeDetailViewModel::class.java.simpleName
    val movieData: MutableLiveData<MovieModel> by lazy { MutableLiveData<MovieModel>() }
    private val disposables = CompositeDisposable()
    val loadingProgressBar: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }

    init {
        getEpisode()
    }

    private fun getEpisode() {
        loadingProgressBar.postValue(true)
        disposables.add(
            ApiClient().getEpisode(episode, seasonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError)
        )
    }


    private fun onSuccess(movieModel: MovieModel) {
        Log.d(TAG, "onSuccess")
        loadingProgressBar.postValue(false)
        AppDatabase.getInstance().movieDao().deleteMovieByTitle(movieModel.title!!)
        movieData.postValue(movieModel)
        AppDatabase.getInstance().movieDao().insert(movieModel)
    }

    private fun onError(e: Throwable) {
        Log.e(TAG, "onError: " + e.message)
        AppDatabase.getInstance().movieDao().getMovieByTitle(episodeTitle)?.let {
            movieData.postValue(it)
            loadingProgressBar.postValue(false)
        } ?: loadingProgressBar.postValue(true)
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}