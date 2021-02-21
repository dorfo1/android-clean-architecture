package com.example.koinsample.domain.interactor

import com.example.koinsample.core.base.FlowlableUseCase
import com.example.koinsample.core.base.Resource
import com.example.koinsample.domain.entity.Clip

abstract class FetchClips : FlowlableUseCase<String, Resource<List<Clip>>>()