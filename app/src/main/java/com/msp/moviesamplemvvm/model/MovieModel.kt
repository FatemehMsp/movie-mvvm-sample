package com.msp.moviesamplemvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
class MovieModel {

     @PrimaryKey(autoGenerate = true)
     var id: Int? = 0
     var title: String? = ""
     var year: String? = null
     var rated: String? = null
     var released: String? = null
     var runtime: String? = null
     var genre: String? = null
     var director: String? = null
     var writer: String? = null
     var actors: String? = null
     var plot: String? = null
     var language: String? = null
     var country: String? = null
     var awards: String? = null
     var poster: String? = null
     var metascore: String? = null
     var imdbRating: String? = null
     var imdbVotes: String? = null
     var imdbID: String? = null
     var type: String? = null
     var totalSeasons: String? = null
     var response: String? = null
}