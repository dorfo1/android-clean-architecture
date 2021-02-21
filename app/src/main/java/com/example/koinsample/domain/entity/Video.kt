package com.example.koinsample.domain.entity

import com.example.koinsample.core.extensions.setSizeToUrl
import com.example.koinsample.core.extensions.setSizeToVideoThumbnail
import com.example.koinsample.data.model.VideoResponse

data class Video(
    val id : String,
    val user : String,
    val title : String,
    val description : String,
    val thumbnail : String,
){

    companion object {
        fun fromResponse(response : VideoResponse) : Video {
            return Video(
                id = response.id ?: "",
                user = response.user ?: "",
                title = response.title ?: "",
                description = response.description ?: "",
                thumbnail = response.thumbnail?.setSizeToVideoThumbnail() ?: "",
            )
        }
    }
}