package com.github.fatemehmsp.movie.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.fatemehmsp.movie.R
import com.github.fatemehmsp.movie.data.model.MovieModel
import com.github.fatemehmsp.movie.data.database.AppDatabase
import com.github.fatemehmsp.movie.Api.ApiService
import com.github.fatemehmsp.movie.util.SeasonList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by Fatemeh Movassaghpour on 10/19/2019.
 */
class MainViewModel(val apiService: ApiService,val database: AppDatabase)  : ViewModel() {

    private val TAG: String = MainViewModel::class.java.simpleName
    val movieData: MutableLiveData<MovieModel> by lazy { MutableLiveData<MovieModel>() }
    private val disposables = CompositeDisposable()
    val loadingProgressBar: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }
    val message: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }


    init {
        getMovieData()
    }

    private fun getMovieData() {
        loadingProgressBar.postValue(true)
        disposables.add(
            apiService.getMovie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError)
        )
    }

    private fun onSuccess(movieModel: MovieModel) {
        message.postValue(R.string.message_online)
        loadingProgressBar.postValue(false)
        database.movieDao().deleteMovieByTitle(MOVIE_TITLE)
        database.movieDao().insert(movieModel)
        movieModel.seasonList = SeasonList.getSeasonList()
        movieData.postValue(movieModel)
    }

    private fun onError(e: Throwable) {
        Log.e(TAG, "onError: " + e.message)
        database.movieDao().getMovieByTitle(MOVIE_TITLE)?.let {
            message.postValue(R.string.message_offline)
            it.seasonList = SeasonList.getSeasonList()
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