package com.msp.moviesamplemvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode")
class EpisodeModel {

     @PrimaryKey(autoGenerate = true)
     var id: Int? = 0
     var title: String? = null
     var released: String? = null
     var episode: String? = null
     var season: String? = null
     var imdbRating: String? = null
     var imdbID: String? = null
    
}