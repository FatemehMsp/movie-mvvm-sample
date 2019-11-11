package com.github.fatemehmsp.movie.util

import android.content.Context
import java.io.IOException

/**
 * Created by Fatemeh Movassaghpour on 11/6/2019.
 */
class GsonDataReader {

    fun getAssetJsonData(context: Context, filename: String): String {
        var json: String? = ""
        try {
            val `is` = context.assets.open(filename)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }

        return json
    }
}