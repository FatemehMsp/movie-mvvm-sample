package com.github.fatemehmsp.movie.data.repository

import android.annotation.SuppressLint
import com.github.fatemehmsp.movie.Api.ApiService
import com.github.fatemehmsp.movie.data.database.EpisodeDao
import com.github.fatemehmsp.movie.data.database.MovieDao
import com.github.fatemehmsp.movie.data.model.EpisodeModel
import com.github.fatemehmsp.movie.data.model.SeasonResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Fatemeh Movassaghpour on 3/23/2020.
 */
@Singleton
class EpisodeRepository @Inject constructor(
    private val episodeDao: EpisodeDao
    ,private val apiService: ApiService
) {

    fun getSeason(seasonId:Int):Single<MutableList<EpisodeModel>>{
        return apiService.getSeason(seasonId).flatMap {
                setSeasonForEpisode(seasonId,it)
        }.onErrorReturn {
            if (episodeDao.getSeasonEpisodes(seasonId).isNotEmpty())
                episodeDao.getSeasonEpisodes(seasonId)
            else
                null
        }
    }

    private fun setSeasonForEpisode(seasonId:Int,seasonResponse: SeasonResponse) : Single<MutableList<EpisodeModel>> {
        episodeDao.deleteSeasonEpisodes(seasonId)
        seasonResponse.episodes.forEach {
            it.season = seasonId
        }
        episodeDao.insertEpisodeList(seasonResponse.episodes)
        return Single.just(seasonResponse.episodes)
    }

}