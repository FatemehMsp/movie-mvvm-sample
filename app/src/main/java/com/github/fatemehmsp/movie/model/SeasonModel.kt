package com.github.fatemehmsp.movie.model

import android.content.Context
import com.github.fatemehmsp.movie.util.GsonDataReader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Fatemeh Movassaghpour on 10/19/2019.
 */
class SeasonModel {

    var id: Int = 0
    var title: String = ""

    fun getSeasonFromJson(context: Context): MutableList<SeasonModel> {
        return Gson().fromJson<MutableList<SeasonModel>>(
            GsonDataReader().getAssetJsonData(
                context,
                "seasonList.gson"
            ), object : TypeToken<MutableList<SeasonModel>>() {}.type
        )
    }

}