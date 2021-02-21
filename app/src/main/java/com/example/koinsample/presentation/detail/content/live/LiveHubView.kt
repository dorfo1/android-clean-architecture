package com.example.koinsample.presentation.detail.content.live

import android.content.Context
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.koinsample.R
import com.example.koinsample.core.base.HubView
import com.example.koinsample.core.base.Resource
import com.example.koinsample.core.extensions.showBaseError
import com.example.koinsample.databinding.LayoutContentBinding
import com.example.koinsample.domain.entity.Game
import com.example.koinsample.domain.entity.Stream
import com.example.koinsample.presentation.detail.DetailViewModel
import com.example.koinsample.presentation.detail.content.adapter.BaseContentAdapter
import com.example.koinsample.presentation.detail.content.adapter.BaseContentItem
import org.koin.core.inject

class LiveHubView(
    private val game: Game,
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner,
    private val detailViewModel: DetailViewModel
) : HubView<DetailViewModel>(context, lifecycleOwner, detailViewModel) {

    private val contentAdapter: BaseContentAdapter by lazy {
        BaseContentAdapter()
    }

    private val viewModel : LiveHubViewModel by inject()

    private var title : TextView? = null
    private var recycler : RecyclerView? = null
    private var progress : ProgressBar? = null

    override fun getLayout(): Int = R.layout.layout_content

    override fun onViewCreated() {
        title = view?.findViewById(R.id.title)
        recycler = view?.findViewById(R.id.rvContent)
        progress = view?.findViewById(R.id.progress)

        title?.text = context.getString(R.string.live_hub_title)

        recycler?.apply {
            adapter = contentAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.getStreams(game.id)
        viewModel.streams.observe(lifecycleOwner, Observer(::handleStream))
    }

    private fun handleStream(resource: Resource<List<Stream>>?) {
        if(resource is Resource.Success) showStreams(resource.data)
        progress?.isVisible = resource is Resource.Loading
        if(resource is Resource.Error) context.showBaseError()

    }

    private fun showStreams(data: List<Stream>?) {
        data?.let {
            contentAdapter.items = it.map {stream -> BaseContentItem.fromStream(stream) }.take(6)
        }
    }
}