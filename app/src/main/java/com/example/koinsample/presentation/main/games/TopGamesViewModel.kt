package com.example.koinsample.presentation.main.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.koinsample.core.base.Resource
import com.example.koinsample.data.model.GameResponse
import com.example.koinsample.domain.entity.Game
import com.example.koinsample.domain.interactor.FetchGames
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TopGamesViewModel(
    private val fetchGames: FetchGames
) : ViewModel() {

    private val _games = MutableLiveData<Resource<List<Game>>>()
    val games: LiveData<Resource<List<Game>>> get() = _games


    fun fetchGames(){
        viewModelScope.launch {
            fetchGames.invoke(Unit).collect {
                _games.postValue(it)
            }
        }

    }


}
