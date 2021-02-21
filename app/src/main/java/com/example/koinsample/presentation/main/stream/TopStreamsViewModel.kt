package com.example.koinsample.presentation.main.stream

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koinsample.core.base.Resource
import com.example.koinsample.domain.entity.Stream
import com.example.koinsample.domain.interactor.FetchStreams
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TopStreamsViewModel(
    private val fetchStreams: FetchStreams
) : ViewModel() {

    private val _streams = MutableLiveData<Resource<List<Stream>>>()
    val stream : LiveData<Resource<List<Stream>>> get() = _streams

    fun getStreams(){
        viewModelScope.launch {
            fetchStreams(null).collect {
                _streams.postValue(it)
            }
        }
    }
}