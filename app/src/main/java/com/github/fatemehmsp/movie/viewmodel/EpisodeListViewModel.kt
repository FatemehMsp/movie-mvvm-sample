package com.github.fatemehmsp.movie.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.fatemehmsp.movie.data.model.EpisodeModel
import com.github.fatemehmsp.movie.data.model.SeasonResponse
import com.github.fatemehmsp.movie.data.database.AppDatabase
import com.github.fatemehmsp.movie.Api.ApiService
import com.github.fatemehmsp.movie.data.repository.EpisodeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Fatemeh Movassaghpour on 11/7/2019.
 */
class EpisodeListViewModel(private val episodeRepository: EpisodeRepository) : ViewModel() {
    private val TAG: String = EpisodeListViewModel::class.java.simpleName
    val episodes: MutableLiveData<MutableList<EpisodeModel>> by lazy { MutableLiveData<MutableList<EpisodeModel>>() }
    private val disposables = CompositeDisposable()
    val episodeLoadingProgressBar: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }
    private var seasonId: Int=0


    fun getSeasonEpisodes(seasonId: Int) {
        this.seasonId = seasonId
        episodeLoadingProgressBar.postValue(true)
        disposables.add(
            episodeRepository.getSeason(seasonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError)
        )
    }

    private fun onSuccess(episodesResponse:MutableList<EpisodeModel>) {
        episodeLoadingProgressBar.postValue(false)
        episodes.postValue(episodesResponse)
    }

    private fun onError(e: Throwable) {
        Log.e(TAG, "onError: " + e.message)
        episodeLoadingProgressBar.postValue(true)
    }



    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}