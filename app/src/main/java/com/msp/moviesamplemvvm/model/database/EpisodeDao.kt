package com.msp.moviesamplemvvm.model.database

import androidx.room.Dao
import androidx.room.Query
import com.msp.moviesamplemvvm.model.EpisodeModel
import io.reactivex.Single

/**
 * Created by Fatemeh Movassaghpour on 10/19/2019.
 */
@Dao
interface EpisodeDao : BaseDao<EpisodeModel> {

    @Query("select * from episode where season=:season")
    fun getSeasonEpisodes(season: String): Single<MutableList<EpisodeModel>>

    @Query("delete from episode where season=:season")
    fun deleteSeasonEpisodes(season: String)
}