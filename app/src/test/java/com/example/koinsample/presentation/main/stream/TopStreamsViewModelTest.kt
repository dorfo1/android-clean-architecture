package com.example.koinsample.presentation.main.stream

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.koinsample.core.base.Resource
import com.example.koinsample.di.AppModule
import com.example.koinsample.domain.entity.Stream
import com.example.koinsample.domain.interactor.FetchStreams
import com.example.koinsample.util.MainCoroutineRule
import com.example.koinsample.util.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.mock.declareModule
import java.lang.Exception

@ExperimentalCoroutinesApi
class TopStreamsViewModelTest : KoinTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()


    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: TopStreamsViewModel
    private val fetchStreams = mockk<FetchStreams>()

    init {
        startKoin {
            modules(AppModule.dependencyModules)
            declareModule {
                single { fetchStreams }
            }
        }
    }

    @After
    fun clear() {
        stopKoin()
    }

    @Before
    fun setUp() {
        viewModel = TopStreamsViewModel(fetchStreams)
    }


    @Test
    fun `Streams should be success when fetching is successful`() {
        val streams = viewModel.stream

        coEvery { fetchStreams.invoke(null) } returns flow { emit(Resource.Success<List<Stream>>(listOf())) }
        viewModel.getStreams()
        verify(exactly = 1) { coroutineRule.launch { fetchStreams.invoke(null) } }

        assert(streams.getOrAwaitValue() is Resource.Success)
    }

    @Test
    fun `Streams should be error when fetching is a failure`() {
        val streams = viewModel.stream

        coEvery { fetchStreams.invoke(null) } returns flow { emit(Resource.Error<List<Stream>>(Exception())) }
        viewModel.getStreams()
        verify(exactly = 1) { coroutineRule.launch { fetchStreams.invoke(null) } }

        assert(streams.getOrAwaitValue() is Resource.Error)
    }


}