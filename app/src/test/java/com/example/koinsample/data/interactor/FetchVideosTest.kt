package com.example.koinsample.data.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.koinsample.core.base.Resource
import com.example.koinsample.data.model.VideoList
import com.example.koinsample.data.model.VideoResponse
import com.example.koinsample.data.serivce.TwitchService
import com.example.koinsample.di.AppModule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.mock.declareModule
import java.lang.Exception

import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class FetchVideosTest : KoinTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private lateinit var fetchVideosImpl: FetchVideosImpl
    private val service: TwitchService = mockk<TwitchService>()


    init {
        startKoin {
            modules(AppModule.dependencyModules)
            declareModule {
                single { service }
            }
        }
    }

    @After
    fun clear() {
        stopKoin()
    }

    @Before
    fun setUp() {
        fetchVideosImpl = FetchVideosImpl(service)
    }


    @Test
    fun `Should emit success when service returns success`() {
        val videoList =
            VideoList(listOf(VideoResponse("id", "user", "title", "description", "thumb")))

        coEvery { service.getVideos("game") }.returns(videoList)

        runBlocking {
            fetchVideosImpl.invoke("game").test {
                assert(expectItem() is Resource.Loading)
                assert(expectItem() is Resource.Success)
                expectComplete()
            }
        }

        verify(exactly = 1) { runBlocking { service.getVideos("game") } }

    }

    @Test
    fun `Should emit error when service returns error`() = runBlocking {
        coEvery { service.getVideos("game") }.throws(Exception())


        fetchVideosImpl("game").test {
            assert(expectItem() is Resource.Loading)
            assert(expectItem() is Resource.Error)
            expectComplete()
        }
    }


}