package com.example.koinsample.data.model

import com.google.gson.annotations.SerializedName

data class GameResponse(
    val id : String?,
    val name : String?,
    @SerializedName("box_art_url")
    val image : String?
)


data class GameList(
    @SerializedName("data")
    val games : List<GameResponse>,
    val pagination : Pagination?
)

data class Pagination(
    val cursor : String?
)