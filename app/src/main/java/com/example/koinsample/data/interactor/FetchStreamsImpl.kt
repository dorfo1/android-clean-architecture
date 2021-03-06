package com.example.koinsample.data.interactor


import com.example.koinsample.core.base.Resource
import com.example.koinsample.core.extensions.toFlowResource
import com.example.koinsample.domain.entity.Stream
import com.example.koinsample.domain.interactor.FetchStreams
import com.example.koinsample.data.serivce.TwitchService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class FetchStreamsImpl(
    private val service: TwitchService
) : FetchStreams() {

    override suspend fun execute(params: String?): Flow<Resource<List<Stream>>> = flow {
            val response = service.getStreams(game = params)
            emit(response.streams.map { stream -> Stream.fromResponse(stream) })
    }.toFlowResource()
}
