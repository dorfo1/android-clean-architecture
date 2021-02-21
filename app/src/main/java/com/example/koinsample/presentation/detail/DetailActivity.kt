package com.example.koinsample.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import com.example.koinsample.R
import com.example.koinsample.core.base.HubView
import com.example.koinsample.databinding.ActivityDetailBinding
import com.example.koinsample.domain.entity.Game
import com.example.koinsample.presentation.detail.hub.DetailHub
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class DetailActivity : AppCompatActivity() {

    private val viewModel : DetailViewModel by viewModel()

    companion object{
        private const val GAME_KEY = "GAME"

        fun startActivity(context: Context?,game:Game){
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra(GAME_KEY,game)
            

            context?.startActivity(intent)
        }
    }

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val game = intent.getParcelableExtra<Game>(GAME_KEY)
        handleHubContent(game,viewModel.getHubContent())

        supportActionBar?.title = game?.name
    }

    private fun handleHubContent(game: Game?, hubContent: List<DetailHub>) {
        hubContent.forEach {
            val constructor = try {
                it.kClass.java.getConstructor(Game::class.java,Context::class.java,LifecycleOwner::class.java,DetailViewModel::class.java)
            } catch (ex : Exception){
                null
            }

            constructor?.let {
                val hubView : HubView<DetailViewModel> = constructor.newInstance(game,applicationContext,this,viewModel) as HubView<DetailViewModel>
                hubView.apply {
                    inflate(binding.content)
                }
            }
        }
    }


}