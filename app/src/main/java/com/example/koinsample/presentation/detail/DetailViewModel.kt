package com.example.koinsample.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koinsample.presentation.detail.hub.DetailHub

class DetailViewModel : ViewModel() {

    fun getHubContent() : List<DetailHub>{
       return listOf(DetailHub.LIVE,DetailHub.VIDEOS,DetailHub.CLIP)
    }
}