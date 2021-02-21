package com.example.koinsample.domain.entity

import com.example.koinsample.data.model.ClipResponse

data class Clip(
    val id: String,
    val title: String,
    val user: String,
    val thumbnail: String,
){

    companion object{
        fun fromResponse(response : ClipResponse) : Clip{
            return Clip(
                id = response.id ?: "",
                title = response.title ?: "",
                user = response.user ?: "",
                thumbnail = response.thumbnail_url ?: ""
            )
        }
    }
}