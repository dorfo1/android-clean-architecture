package com.example.koinsample.presentation.main.games.adapter

import BaseRecyclerViewAdapter
import BaseRecyclerViewHolder
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.koinsample.R
import com.example.koinsample.databinding.AdapterGameItemBinding
import com.example.koinsample.domain.entity.Game

class GamesAdapter(private val listener: (Game) -> Unit) : BaseRecyclerViewAdapter<Game,GamesAdapter.GameHolder>(){

    companion object{

    }

    class GameHolder(private val binding: AdapterGameItemBinding, private val listener: (Game) -> Unit) : BaseRecyclerViewHolder<Game>(binding.root) {
        override fun bind(item : Game){
            Glide.with(binding.image).load(item.image).into(binding.image)
            binding.title.text = item.name

            val anim = ValueAnimator.ofFloat(0f,1f)

            anim.addUpdateListener {
                val value = it.animatedValue as Float

                binding.root.scaleX = value
                binding.root.scaleY = value
            }


            anim.start()

            binding.root.setOnClickListener {

                val translationAnim = ObjectAnimator.ofFloat(binding.image,View.TRANSLATION_X,10f,-10f,0f)
                translationAnim.duration = 150L
                translationAnim.repeatCount = 2

                translationAnim.start()

                translationAnim.doOnEnd {
                    listener.invoke(item)
                }
            }
        }
    }

    override fun getLayoutResource(viewType: Int): Int  = R.layout.adapter_game_item

    override fun createViewHolder(view: View, viewType: Int): GameHolder {
        val bind = AdapterGameItemBinding.bind(view)
        return GameHolder(bind,listener)
    }


}