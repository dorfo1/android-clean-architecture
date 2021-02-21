package com.example.koinsample.di

import com.example.koinsample.core.extensions.resolveRetrofitService
import com.example.koinsample.data.interactor.FetchClipsImpl
import com.example.koinsample.data.interactor.FetchGamesImpl
import com.example.koinsample.data.interactor.FetchStreamsImpl
import com.example.koinsample.data.interactor.FetchVideosImpl
import com.example.koinsample.domain.interactor.FetchGames
import com.example.koinsample.domain.interactor.FetchStreams
import com.example.koinsample.data.serivce.TwitchService
import com.example.koinsample.domain.interactor.FetchClips
import com.example.koinsample.domain.interactor.FetchVideos
import com.example.koinsample.presentation.detail.DetailViewModel
import com.example.koinsample.presentation.detail.content.clip.ClipHubViewModel
import com.example.koinsample.presentation.detail.content.live.LiveHubViewModel
import com.example.koinsample.presentation.detail.content.video.VideoHubViewModel
import com.example.koinsample.presentation.main.games.TopGamesViewModel
import com.example.koinsample.presentation.main.stream.TopStreamsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    
    val dependencyModules = module {
        //service
        single<TwitchService> { resolveRetrofitService() }

        //usecases
        factory<FetchGames> { FetchGamesImpl(get()) }
        factory<FetchStreams> {  FetchStreamsImpl(get()) }
        factory<FetchVideos> { FetchVideosImpl(get()) }
        factory<FetchClips> { FetchClipsImpl(get()) }

        //viewmodels
        viewModel<TopGamesViewModel> { TopGamesViewModel(get()) }
        viewModel<TopStreamsViewModel> { TopStreamsViewModel(get()) }
        viewModel<DetailViewModel> { DetailViewModel() }
        viewModel<ClipHubViewModel> { ClipHubViewModel(get()) }
        viewModel<LiveHubViewModel> { LiveHubViewModel(get()) }
        viewModel<VideoHubViewModel> { VideoHubViewModel(get()) }
    }
}