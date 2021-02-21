package com.example.koinsample.domain.entity

import android.os.Parcelable
import com.example.koinsample.core.extensions.setSizeToUrl
import com.example.koinsample.data.model.StreamResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stream(
    val id : String,
    val gameName : String,
    val userId : String,
    val userName : String,
    val title : String,
    val viewers : Int,
    val thumbnail : String
) : Parcelable {

    companion object{
        fun fromResponse(response : StreamResponse) : Stream = Stream(
            id = response.id ?: "",
            gameName = response.gameName ?: "",
            title =  response.title ?: "",
            userId = response.user_id ?: "",
            userName = response.user_name ?: "",
            viewers = response.viewers ?: 0,
            thumbnail = response.thumbnail?.setSizeToUrl() ?: ""
        )
    }
}
