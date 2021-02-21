package com.example.koinsample.domain.interactor

import com.example.koinsample.core.base.FlowlableUseCase
import com.example.koinsample.core.base.Resource
import com.example.koinsample.domain.entity.Clip
import com.example.koinsample.domain.entity.Video

abstract class FetchVideos : FlowlableUseCase<String, Resource<List<Video>>>()