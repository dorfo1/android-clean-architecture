package com.example.koinsample.domain.interactor

import com.example.koinsample.core.base.FlowlableUseCase
import com.example.koinsample.core.base.Resource
import com.example.koinsample.domain.entity.Game

abstract class FetchGames : FlowlableUseCase<Unit,Resource<List<Game>>>()