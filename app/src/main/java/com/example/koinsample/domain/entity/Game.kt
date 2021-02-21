package com.example.koinsample.domain.entity

import android.os.Parcelable
import com.example.koinsample.core.extensions.setSizeToUrl
import com.example.koinsample.data.model.GameResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id : String,
    val name : String,
    val image : String
) : Parcelable {

    companion object {
        fun fromResponse(gameResponse: GameResponse) : Game{
            return Game(
                id = gameResponse.id ?: "",
                name = gameResponse.name ?: "",
                image = gameResponse.image?.setSizeToUrl() ?: ""
            )
        }
    }
}