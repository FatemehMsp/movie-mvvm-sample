package com.github.fatemehmsp.movie.util

import com.github.fatemehmsp.movie.data.model.SeasonModel

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */
object SeasonList {

    fun getSeasonList() : MutableList<SeasonModel> {
        val seasonList = arrayListOf<SeasonModel>()
        seasonList.add(SeasonModel().addSeason(1,"Season 1"))
        seasonList.add(SeasonModel().addSeason(2,"Season 2"))
        seasonList.add(SeasonModel().addSeason(3,"Season 3"))
        seasonList.add(SeasonModel().addSeason(4,"Season 4"))
        seasonList.add(SeasonModel().addSeason(5,"Season 5"))
        seasonList.add(SeasonModel().addSeason(6,"Season 6"))
        seasonList.add(SeasonModel().addSeason(7,"Season 7"))
        seasonList.add(SeasonModel().addSeason(8,"Season 8"))
        return seasonList
    }
}