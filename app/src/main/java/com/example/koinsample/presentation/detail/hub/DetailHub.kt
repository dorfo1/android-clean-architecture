package com.example.koinsample.presentation.detail.hub

import com.example.koinsample.presentation.detail.content.clip.ClipHubView
import com.example.koinsample.presentation.detail.content.live.LiveHubView
import com.example.koinsample.presentation.detail.content.video.VideoHubView
import kotlin.reflect.KClass

enum class DetailHub(
    val kClass: KClass<*>
) {
    LIVE(LiveHubView::class), CLIP(ClipHubView::class), VIDEOS(VideoHubView::class)
}