package com.github.fatemehmsp.movie.data.model

/**
 * Created by Fatemeh Movassaghpour on 10/19/2019.
 */
class SeasonModel{

    var id: Int =0
    var title: String =""

    fun addSeason(id:Int,title:String) : SeasonModel{
        this.id = id
        this.title = title
        return this
    }
}