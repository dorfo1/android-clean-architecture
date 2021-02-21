package com.example.koinsample.data.interactor

import com.example.koinsample.core.base.Resource
import com.example.koinsample.data.serivce.TwitchService
import com.example.koinsample.domain.entity.Video
import com.example.koinsample.domain.interactor.FetchVideos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class FetchVideosImpl(
    private val service: TwitchService
) : FetchVideos() {

    override suspend fun execute(params: String): Flow<Resource<List<Video>>> = flow {
        emit(Resource.Loading())
        val response = service.getVideos(params)
        emit(Resource.Success(response.data.map { Video.fromResponse(it) }))
    }.catch {
        emit(Resource.Error<List<Video>>(Exception(it)))
    }
}