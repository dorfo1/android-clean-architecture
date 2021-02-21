package com.example.koinsample.data.model

import com.google.gson.annotations.SerializedName

data class StreamResponse(
    val id : String?,
    @SerializedName("game_name")
    val gameName : String?,
    val user_name : String?,
    val user_id : String?,
    val title : String?,
    @SerializedName("viewer_count")
    val viewers : Int?,
    @SerializedName("thumbnail_url")
    val thumbnail : String?
)


data class StreamList(
    @SerializedName("data")
    val streams : List<StreamResponse>
)



