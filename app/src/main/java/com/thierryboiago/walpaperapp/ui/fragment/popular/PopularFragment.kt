package com.thierryboiago.walpaperapp.ui.fragment.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.thierryboiago.core.domain.model.PhotoDomain
import com.thierryboiago.walpaperapp.databinding.FragmentPopularBinding
import com.thierryboiago.walpaperapp.ui.fragment.adapter.photoadapter.PhotoAdapter
import com.thierryboiago.walpaperapp.ui.fragment.main.MainFragmentDirections
import com.thierryboiago.walpaperapp.ui.fragment.popular.viewmodel.PopularViewModel
import com.thierryboiago.walpaperapp.util.animationCancel
import com.thierryboiago.walpaperapp.util.pulseAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularFragment : Fragment() {
    private lateinit var binding: FragmentPopularBinding
    private val viewModel: PopularViewModel by viewModels()
    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observeLoadState()
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.popularWallpaper().collect { pagingData ->
                    photoAdapter.submitData(pagingData)

                }
            }
        }
    }

    private fun observeLoadState() {
        lifecycleScope.launch {
            photoAdapter.loadStateFlow.collectLatest { loadState ->
                binding.imagePulseAnimation.isVisible =
                    loadState.source.refresh is LoadState.Loading
                when (loadState.refresh) {
                    is LoadState.Loading -> { binding.imagePulseAnimation.pulseAnimation()}
                    is LoadState.NotLoading -> { binding.imagePulseAnimation.animationCancel()}
                    is LoadState.Error -> {
                        binding.imagePulseAnimation.animationCancel()
                        Toast.makeText(requireContext(), "try again later!", Toast.LENGTH_SHORT)
                    }
                }
            }
        }
    }

    private fun initAdapter() {
        photoAdapter = PhotoAdapter(::detail)
        val gridLayoutManager = GridLayoutManager(context, 3)
        with(binding.recyclerView) {
            scrollToPosition(0)
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = photoAdapter
        }
    }

    private fun detail(photo: PhotoDomain) {
        val data = arrayOf(photo.srcDomain?.original, photo.avgColor)
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDownloadFragment(data))
    }


}