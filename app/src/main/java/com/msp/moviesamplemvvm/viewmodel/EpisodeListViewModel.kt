package com.msp.moviesamplemvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msp.moviesamplemvvm.model.EpisodeModel
import com.msp.moviesamplemvvm.model.SeasonResponse
import com.msp.moviesamplemvvm.model.database.AppDatabase
import com.msp.moviesamplemvvm.network.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
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
                .subscribe(this::onSuccess, this::onError)
        )
    }

    private fun onSuccess(seasonResponse: SeasonResponse) {
        Log.d(TAG, "onSuccess")
        episodeLoadingProgressBar.postValue(false)
        setSeasonForEpisode(seasonResponse)
    }

    private fun onError(e: Throwable) {
        AppDatabase.getInstance().episodeDao().getSeasonEpisodes(seasonId)?.let {
            episodeLoadingProgressBar.postValue(false)
            episodes.postValue(it)
        } ?: episodeLoadingProgressBar.postValue(true)
        Log.e(TAG, "onError: " + e.message)
    }

    private fun setSeasonForEpisode(seasonResponse: SeasonResponse) {
        AppDatabase.getInstance().episodeDao().deleteSeasonEpisodes(seasonId)
        seasonResponse.episodes.forEach {
            it.season = seasonId
            //AppDatabase.getInstance().episodeDao().insert(it)
        }
        episodes.postValue(seasonResponse.episodes)
        AppDatabase.getInstance().episodeDao().insertEpisodeList(seasonResponse.episodes)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}