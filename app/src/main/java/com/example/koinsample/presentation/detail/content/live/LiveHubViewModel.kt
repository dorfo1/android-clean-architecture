package com.example.koinsample.presentation.detail.content.live

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koinsample.core.base.Resource
import com.example.koinsample.domain.entity.Stream
import com.example.koinsample.domain.interactor.FetchStreams
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LiveHubViewModel(
    private val fetchStreams: FetchStreams
) : ViewModel() {

    private val _streams = MutableLiveData<Resource<List<Stream>>>()
    val streams : LiveData<Resource<List<Stream>>> get() = _streams


    fun getStreams(gameId : String){
        viewModelScope.launch {
            fetchStreams(gameId).collect {
                _streams.postValue(it)
            }
        }
    }
}