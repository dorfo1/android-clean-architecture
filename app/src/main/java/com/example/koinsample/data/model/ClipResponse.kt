package com.example.koinsample.data.model

import com.google.gson.annotations.SerializedName

data class ClipResponse(
    val id : String?,
    val title : String?,
    @SerializedName("broadcaster_name")
    val user : String?,
    val thumbnail_url : String?,
)

data class ClipList(
    val data : List<ClipResponse>
)