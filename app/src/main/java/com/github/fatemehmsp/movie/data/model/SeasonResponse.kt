package com.github.fatemehmsp.movie.data.model

import com.google.gson.annotations.SerializedName

class SeasonResponse {

    var title: String? = null
    var season: String? = null
    var totalSeasons: String? = null
    @SerializedName("Episodes")
    var episodes: MutableList<EpisodeModel> = arrayListOf()
    var response: String? = null

}