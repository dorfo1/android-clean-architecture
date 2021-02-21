package com.example.koinsample.data.interactor

import com.example.koinsample.core.base.Resource
import com.example.koinsample.data.serivce.TwitchService
import com.example.koinsample.domain.entity.Clip
import com.example.koinsample.domain.interactor.FetchClips
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class FetchClipsImpl(
    private val service: TwitchService
) : FetchClips() {

    override suspend fun execute(params: String): Flow<Resource<List<Clip>>> = flow {
        emit(Resource.Loading())
        try {
            val response = service.getClips(params)
            emit(Resource.Success(response.data.map { Clip.fromResponse(it) }))
        } catch (ex : Exception){
            emit(Resource.Error<List<Clip>>(ex))
        }
    }
}