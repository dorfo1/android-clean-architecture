package com.example.koinsample.data.model

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    val id : String?,
    @SerializedName("user_name")
    val user : String?,
    val title : String?,
    val description : String?,
    @SerializedName("thumbnail_url")
    val thumbnail : String?,
)

data class VideoList(
    val data : List<VideoResponse>
)