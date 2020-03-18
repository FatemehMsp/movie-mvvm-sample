package com.github.fatemehmsp.movie.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "episode")
class EpisodeModel {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @SerializedName("Title")
    var title: String? = null
    @SerializedName("Released")
    var released: String? = null
    @SerializedName("Episode")
    var episode: String? = null
    var season: Int = 0
    var imdbRating: String? = null

}