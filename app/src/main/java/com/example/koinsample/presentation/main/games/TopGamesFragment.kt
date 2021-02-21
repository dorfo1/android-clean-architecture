package com.example.koinsample.presentation.main.games

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.koinsample.R
import com.example.koinsample.core.base.Resource
import com.example.koinsample.databinding.FragmentGamesBinding
import com.example.koinsample.domain.entity.Game
import com.example.koinsample.presentation.detail.DetailActivity
import com.example.koinsample.presentation.main.games.adapter.GamesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopGamesFragment : Fragment(R.layout.fragment_games) {

    private var _binding: FragmentGamesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TopGamesViewModel by viewModel()

    private val gamesAdapter: GamesAdapter by lazy {
        GamesAdapter {
           DetailActivity.startActivity(context,it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentGamesBinding.bind(view)

        binding.rvGames.apply {
            adapter = gamesAdapter
            layoutManager = GridLayoutManager(context,2)
        }
        setupObservers()
        viewModel.fetchGames()

    }

    private fun setupObservers() {
        viewModel.games.observe(viewLifecycleOwner, Observer {
            if(it is Resource.Success) showItems(it.data)
            binding.progress.isVisible = it is Resource.Loading
            if(it is Resource.Error) showError()
        })
    }

    private fun showItems(data: List<Game>?) {
        data?.let {
            gamesAdapter.items = it
        }
    }

    private fun showError() {
        Toast.makeText(context,getString(R.string.default_error_message),Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun navigateToGamePage(it: Game) {
        println("nav to ${it.name}")
    }
}