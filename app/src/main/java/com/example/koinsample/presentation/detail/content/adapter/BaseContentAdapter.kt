package com.example.koinsample.presentation.detail.content.adapter

import BaseRecyclerViewAdapter
import BaseRecyclerViewHolder
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.koinsample.R
import com.example.koinsample.domain.entity.Clip
import com.example.koinsample.domain.entity.Stream
import com.example.koinsample.domain.entity.Video

class BaseContentAdapter :
    BaseRecyclerViewAdapter<BaseContentItem, BaseContentAdapter.ContentViewHolder>() {


    override fun getItemCount(): Int = items.size

    override fun getLayoutResource(viewType: Int): Int = R.layout.adapter_detail_item


    override fun createViewHolder(view: View, viewType: Int): ContentViewHolder {
        return ContentViewHolder(view)
    }

    class ContentViewHolder(val view: View) :
        BaseRecyclerViewHolder<BaseContentItem>(view) {

        override fun bind(item: BaseContentItem) {
            val image = view.findViewById<ImageView>(R.id.thumbnail)
            val title = view.findViewById<TextView>(R.id.title)
            val user = view.findViewById<TextView>(R.id.user)

            Glide.with(image).load(item.thumbnail).into(image)
            title.text = item.title
            user.text = item.user
        }

    }
}


data class BaseContentItem(
    val title: String,
    val thumbnail: String,
    val user: String,
) {

    companion object {
        fun fromClip(clip: Clip): BaseContentItem {
            return BaseContentItem(
                title = clip.title,
                thumbnail = clip.thumbnail,
                user = clip.user
            )
        }

        fun fromVideo(video: Video): BaseContentItem {
            return BaseContentItem(
                title = video.title,
                thumbnail = video.thumbnail,
                user = video.user
            )
        }

        fun fromStream(stream: Stream): BaseContentItem {
            return BaseContentItem(
                title = stream.title,
                thumbnail = stream.thumbnail,
                user = stream.userName
            )
        }
    }
}