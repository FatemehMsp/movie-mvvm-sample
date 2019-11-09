package com.msp.moviesamplemvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msp.moviesamplemvvm.model.EpisodeModel
import com.msp.moviesamplemvvm.model.SeasonResponse
import com.msp.moviesamplemvvm.network.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by Fatemeh Movassaghpour on 11/7/2019.
 */
class EpisodeListViewModel(private val seasonId: Int) : ViewModel() {
    private val TAG: String = EpisodeListViewModel::class.java.simpleName
    val episodes: MutableLiveData<MutableList<EpisodeModel>> by lazy { MutableLiveData<MutableList<EpisodeModel>>() }
    private val disposables = CompositeDisposable()
    val episodeLoadingProgressBar: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }

    init {
        getSeasonEpisodes()
    }

    private fun getSeasonEpisodes() {
        episodeLoadingProgressBar.postValue(true)
        disposables.add(
            ApiClient().getSeason(seasonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SeasonResponse>() {
                    override fun onSuccess(seasonResponse: SeasonResponse) {
                        Log.d(TAG, "onSuccess")
                        episodeLoadingProgressBar.postValue(false)
                        setSeasonForEpisode(seasonResponse)
                    }

                    override fun onError(e: Throwable) {
                        episodeLoadingProgressBar.postValue(false)
                        Log.e(TAG, "onError: " + e.message)
                    }
                }
                )
        )
    }

    private fun setSeasonForEpisode(seasonResponse: SeasonResponse) {
        seasonResponse.Episodes?.forEach {
            it.season = seasonId
        }
        episodes.postValue(seasonResponse.Episodes)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}