package com.example.koinsample.presentation.main.stream.adapter

import BaseRecyclerViewAdapter
import BaseRecyclerViewHolder
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.text.bold
import com.bumptech.glide.Glide
import com.example.koinsample.R
import com.example.koinsample.databinding.AdapterStreamItemBinding
import com.example.koinsample.domain.entity.Stream

class LiveStreamAdapter(private val listener : (Stream) -> Unit) : BaseRecyclerViewAdapter<Stream,LiveStreamAdapter.LiveStreamViewHolder>() {

    override fun getLayoutResource(viewType: Int): Int = R.layout.adapter_stream_item

    override fun createViewHolder(view: View, viewType: Int): LiveStreamViewHolder {
        val binding = AdapterStreamItemBinding.bind(view)
        return LiveStreamViewHolder(binding,listener)
    }

    class LiveStreamViewHolder(private val binding : AdapterStreamItemBinding,private val listener: (Stream) -> Unit) : BaseRecyclerViewHolder<Stream>(binding.root) {

        override fun bind(item: Stream) {
            binding.apply {
                Glide.with(binding.thumbnail).load(item.thumbnail).into(binding.thumbnail)
                title.text = item.title
                viewers.text = item.viewers.toString()
                game.text = SpannableStringBuilder()
                    .bold { append(item.userName) }
                    .append(" - ")
                    .append(item.gameName)

                root.setOnClickListener {
                    listener.invoke(item)
                }
            }
        }
    }
}