package com.github.fatemehmsp.movie.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.fatemehmsp.movie.data.model.MovieModel
import com.github.fatemehmsp.movie.data.database.AppDatabase
import com.github.fatemehmsp.movie.Api.ApiService
import com.github.fatemehmsp.movie.data.repository.MovieRepository
import com.github.fatemehmsp.movie.util.Constants.MOVIE_TITLE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Fatemeh Movassaghpour on 11/10/2019.
 */
class EpisodeDetailViewModel@Inject constructor(private val movieRepository: MovieRepository): ViewModel() {

    private val TAG: String = EpisodeDetailViewModel::class.java.simpleName
    val movieData: MutableLiveData<MovieModel> by lazy { MutableLiveData<MovieModel>() }
    private val disposables = CompositeDisposable()
    val loadingProgressBar: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }

    fun getEpisode(episode:String,seasonId:Int,episodeTitle: String) {
        loadingProgressBar.postValue(true)
        disposables.add(
            movieRepository.getEpisode(episode, seasonId,episodeTitle)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError)
        )
    }


    private fun onSuccess(movieModel: MovieModel) {
        loadingProgressBar.postValue(false)
        movieData.postValue(movieModel)
    }

    private fun onError(e: Throwable) {
        Log.e(TAG, "onError: " + e.message)
        loadingProgressBar.postValue(true)
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}