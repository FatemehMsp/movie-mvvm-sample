package com.msp.moviesamplemvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode")
class EpisodeModel {

     @PrimaryKey(autoGenerate = true)
     var id: Int = 0
    var Title: String? = null
    var Released: String? = null
    var Episode: String? = null
    var season: Int = 0
     var imdbRating: String? = null

}