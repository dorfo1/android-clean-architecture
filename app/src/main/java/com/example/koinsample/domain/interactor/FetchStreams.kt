package com.example.koinsample.domain.interactor

import com.example.koinsample.core.base.FlowlableUseCase
import com.example.koinsample.core.base.Resource
import com.example.koinsample.domain.entity.Stream

abstract class FetchStreams : FlowlableUseCase<String?, Resource<List<Stream>>>()