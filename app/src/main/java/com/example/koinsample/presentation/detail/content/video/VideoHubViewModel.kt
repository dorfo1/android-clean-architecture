package com.example.koinsample.presentation.detail.content.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koinsample.core.base.Resource
import com.example.koinsample.domain.entity.Video
import com.example.koinsample.domain.interactor.FetchVideos
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class VideoHubViewModel(
    private val fetchVideos: FetchVideos
) : ViewModel() {

    private val _videos = MutableLiveData<Resource<List<Video>>>()
    val videos : LiveData<Resource<List<Video>>> get() = _videos


    fun getVideos(gameId : String){
        viewModelScope.launch {
            fetchVideos(gameId).collect {
                _videos.postValue(it)
            }
        }
    }
}