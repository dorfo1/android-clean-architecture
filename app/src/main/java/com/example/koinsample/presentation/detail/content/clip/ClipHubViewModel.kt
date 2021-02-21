package com.example.koinsample.presentation.detail.content.clip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koinsample.core.base.Resource
import com.example.koinsample.domain.entity.Clip
import com.example.koinsample.domain.interactor.FetchClips
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ClipHubViewModel(
    private val fetchClips: FetchClips
) : ViewModel() {

    private val _clips = MutableLiveData<Resource<List<Clip>>>()
    val clip : LiveData<Resource<List<Clip>>> get() = _clips


    fun getClips(gameId : String){
        viewModelScope.launch {
            fetchClips(gameId).collect {
                _clips.postValue(it)
            }
        }
    }




}