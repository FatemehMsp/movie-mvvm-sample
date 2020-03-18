package com.github.fatemehmsp.movie.util

import com.github.fatemehmsp.movie.model.SeasonModel

/**
 * Created by Fatemeh Movassaghpour on 3/18/2020.
 */
object SeasonList {

    fun getSeasonList() : MutableList<SeasonModel> {
        val seasonList = arrayListOf<SeasonModel>()
        seasonList.add(SeasonModel(1,"Season 1"))
        seasonList.add(SeasonModel(2,"Season 2"))
        seasonList.add(SeasonModel(3,"Season 3"))
        seasonList.add(SeasonModel(4,"Season 4"))
        seasonList.add(SeasonModel(5,"Season 5"))
        seasonList.add(SeasonModel(6,"Season 6"))
        seasonList.add(SeasonModel(7,"Season 7"))
        seasonList.add(SeasonModel(8,"Season 8"))
        return seasonList
    }
}