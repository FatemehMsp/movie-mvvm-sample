package com.msp.moviesamplemvvm.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
class MovieModel {

     @PrimaryKey(autoGenerate = true)
     var id: Int = 0
     @SerializedName("Title")
     var title: String? = ""
     @SerializedName("Year")
     var year: String? = ""
     @SerializedName("Rated")
     var rated: String? = ""
     @SerializedName("Released")
     var released: String? = ""
     @SerializedName("Runtime")
     var runtime: String? = ""
     @SerializedName("Genre")
     var genre: String? = ""
     @SerializedName("Director")
     var director: String? = ""
     @SerializedName("Writer")
     var writer: String? = ""
     @SerializedName("Actors")
     var actors: String? = ""
     @SerializedName("Plot")
     var plot: String? = ""
     @SerializedName("Language")
     var language: String? = ""
     @SerializedName("Country")
     var country: String? = ""
     @SerializedName("Awards")
     var awards: String? = ""
     @SerializedName("Poster")
     var poster: String? = ""
     @SerializedName("Metascore")
     var metascore: String? = ""
     @SerializedName("ImdbRating")
     var imdbRating: String? = ""
     @SerializedName("ImdbVotes")
     var imdbVotes: String? = ""
     @SerializedName("ImdbID")
     var imdbID: String? = ""
     @SerializedName("Type")
     var type: String? = ""
     @SerializedName("TotalSeasons")
     var totalSeasons: String? = ""
     @SerializedName("Response")
     var response: String? = ""
     @Ignore
     var seasonList: MutableList<SeasonModel> = arrayListOf()
}