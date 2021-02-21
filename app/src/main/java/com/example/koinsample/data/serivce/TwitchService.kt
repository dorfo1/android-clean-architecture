package com.example.koinsample.data.serivce

import com.example.koinsample.data.model.ClipList
import com.example.koinsample.data.model.GameList
import com.example.koinsample.data.model.StreamList
import com.example.koinsample.data.model.VideoList
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitchService {

    @GET("games/top")
    suspend fun getGames() : GameList

    @GET("streams")
    suspend fun getStreams(@Query("language") language : String = "pt", @Query("game_id") game : String? = null) : StreamList

    @GET("videos")
    suspend fun getVideos(@Query("game_id") game: String) : VideoList

    @GET("clips")
    suspend fun getClips(@Query("game_id") game: String) : ClipList


}