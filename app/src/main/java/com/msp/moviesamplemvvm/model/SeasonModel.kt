package com.msp.moviesamplemvvm.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.msp.moviesamplemvvm.util.GsonDataReader
/**
 * Created by Fatemeh Movassaghpour on 10/19/2019.
 */
class SeasonModel {

    var id: Int? = 0
    var title: String? = ""

    fun getSeasonFromJson(context: Context): MutableList<SeasonModel> {
        return Gson().fromJson<MutableList<SeasonModel>>(
            GsonDataReader().getAssetJsonData(
                context,
                "seasonList.gson"
            ), object : TypeToken<MutableList<SeasonModel>>() {}.type
        )
    }

}