package com.example.koinsample.data.interactor

import androidx.lifecycle.asLiveData
import com.example.koinsample.core.base.Resource
import com.example.koinsample.core.extensions.asResource
import com.example.koinsample.core.extensions.toFlowResource
import com.example.koinsample.domain.entity.Game
import com.example.koinsample.domain.interactor.FetchGames
import com.example.koinsample.data.serivce.TwitchService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class FetchGamesImpl(
    private val service : TwitchService
) : FetchGames() {


    override suspend fun execute(params: Unit): Flow<Resource<List<Game>>> = flow {
        emit(service.getGames())
    }.asResource{
        it.games.map { gameResponse -> Game.fromResponse(gameResponse) }
    }


}