package com.msp.moviesamplemvvm.model

class SeasonResponse {

    var Title: String? = null
    var Season: String? = null
    var totalSeasons: String? = null
    var Episodes: MutableList<EpisodeModel> = arrayListOf()
    var Response: String? = null

}