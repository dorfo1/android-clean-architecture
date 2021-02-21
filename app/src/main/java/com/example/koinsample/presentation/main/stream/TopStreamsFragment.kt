package com.example.koinsample.presentation.main.stream

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koinsample.R
import com.example.koinsample.core.base.Resource
import com.example.koinsample.databinding.FragmentStreamBinding
import com.example.koinsample.domain.entity.Stream
import com.example.koinsample.presentation.main.stream.adapter.LiveStreamAdapter
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopStreamsFragment : Fragment(R.layout.fragment_stream), CoroutineScope by MainScope() {

    private var _binding: FragmentStreamBinding? = null
    val binding get() = _binding!!

    private val viewModel: TopStreamsViewModel by viewModel()

    private val streamAdapter: LiveStreamAdapter by lazy {
        LiveStreamAdapter{
           println(it.title)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentStreamBinding.bind(view)

        binding.apply {
            rvStream.apply {
                adapter = streamAdapter
                layoutManager = LinearLayoutManager(context)
            }

            swipe.setOnRefreshListener {
                streamAdapter.items = listOf()
                viewModel.getStreams()
            }
        }


        viewModel.getStreams()

        viewModel.stream.observe(viewLifecycleOwner, Observer {
            if (it is Resource.Success) {
                binding.swipe.isRefreshing = false
                handleSuccess(it.data)
            }
            binding.progress.isVisible = it is Resource.Loading
            if (it is Resource.Error){
                binding.swipe.isRefreshing = false
                showError()
            }
        })
    }

    private fun handleSuccess(data: List<Stream>?) {
        data?.let {
            streamAdapter.items = it
        }
    }

    private fun showError() {
        Toast.makeText(
            context,
            "Falha ao buscar streams.Tente novamente mais tarde.",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}