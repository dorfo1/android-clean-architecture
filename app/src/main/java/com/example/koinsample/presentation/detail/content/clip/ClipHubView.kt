package com.example.koinsample.presentation.detail.content.clip

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
import com.example.koinsample.domain.entity.Clip
import com.example.koinsample.domain.entity.Game
import com.example.koinsample.presentation.detail.DetailViewModel
import com.example.koinsample.presentation.detail.content.adapter.BaseContentAdapter
import com.example.koinsample.presentation.detail.content.adapter.BaseContentItem
import org.koin.core.inject

class ClipHubView(
    private val game: Game,
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner,
    private val detailViewModel: DetailViewModel
) : HubView<DetailViewModel>(context, lifecycleOwner, detailViewModel) {

    private val contentAdapter : BaseContentAdapter by lazy {
        BaseContentAdapter()
    }

    private val viewModel : ClipHubViewModel by inject()

    private var title : TextView? = null
    private var recycler : RecyclerView? = null
    private var progress : ProgressBar? = null

    override fun getLayout(): Int = R.layout.layout_content

    override fun onViewCreated() {
        title = view?.findViewById<TextView>(R.id.title)
        recycler = view?.findViewById<RecyclerView>(R.id.rvContent)
        progress = view?.findViewById(R.id.progress)

        title?.text = context.getString(R.string.clip_hub_title)

        recycler?.apply {
            adapter = contentAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.getClips(game.id)
        viewModel.clip.observe(lifecycleOwner, Observer(::handleClips))
    }

    private fun handleClips(resource: Resource<List<Clip>>?) {
        if(resource is Resource.Success) showClips(resource.data)
        progress?.isVisible = resource is Resource.Loading
        if(resource is Resource.Error) context.showBaseError()
    }

    private fun showClips(data: List<Clip>?) {
        data?.let {
            contentAdapter.items = it.map { clip -> BaseContentItem.fromClip(clip) }.take(6)
        }
    }
}