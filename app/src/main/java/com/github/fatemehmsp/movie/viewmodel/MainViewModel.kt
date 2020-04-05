package com.github.fatemehmsp.movie.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.fatemehmsp.movie.R
import com.github.fatemehmsp.movie.data.model.MovieModel
import com.github.fatemehmsp.movie.data.repository.MovieRepository
import com.github.fatemehmsp.movie.util.SeasonList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by Fatemeh Movassaghpour on 10/19/2019.
 */
class MainViewModel@Inject constructor(private val movieRepository: MovieRepository)  : ViewModel() {

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
            movieRepository.getMovie(MOVIE_TITLE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError)
        )
    }

    private fun onSuccess(movieModel: MovieModel) {
        loadingProgressBar.postValue(false)
        movieModel.seasonList = SeasonList.getSeasonList()
        movieData.postValue(movieModel)
    }

    private fun onError(e: Throwable) {
        Log.e(TAG, "onError: " + e.message)
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