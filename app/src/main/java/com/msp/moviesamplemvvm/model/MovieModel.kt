package com.msp.moviesamplemvvm.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
class MovieModel {

     @PrimaryKey(autoGenerate = true)
     var id: Int = 0
     var Title: String? = ""
     var Year: String? = ""
     var Rated: String? = ""
     var Released: String? = ""
     var Runtime: String? = ""
     var Genre: String? = ""
     var Director: String? = ""
     var Writer: String? = ""
     var Actors: String? = ""
     var Plot: String? = ""
     var Language: String? = ""
     var Country: String? = ""
     var Awards: String? = ""
     var Poster: String? = ""
     var Metascore: String? = ""
     var ImdbRating: String? = ""
     var ImdbVotes: String? = ""
     var ImdbID: String? = ""
     var Type: String? = ""
     var TotalSeasons: String? = ""
     var Response: String? = ""
     @Ignore
     var seasonList: MutableList<SeasonModel> = arrayListOf()
}