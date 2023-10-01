package com.example.myimagegallery.presentation.fragment.fullscreenimage

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.myimagegallery.R
import com.example.myimagegallery.data.model.Image
import com.example.myimagegallery.databinding.FragmentFullScreenBinding
import com.example.myimagegallery.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class FullScreenImageFragment : Fragment(R.layout.fragment_full_screen) {
    lateinit var binding: FragmentFullScreenBinding
    val viewModel: MainViewModel by activityViewModels()
    val fullScreenViewModel: FullScreenViewModel by viewModels()

    @Inject
    lateinit var fullScreenImageAdapter: FullScreenImageAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFullScreenBinding.bind(view)

        binding.lifecycleOwner = this
        binding.rvFullScreenImages.adapter = fullScreenImageAdapter
        LinearSnapHelper().attachToRecyclerView(binding.rvFullScreenImages)

        viewModel.fullScreenImage.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                fullScreenViewModel.initialize(it)
                viewModel.fullScreenImage.value = null
            }
        }

        fullScreenViewModel.positionToScrollMLD.observe(viewLifecycleOwner) {
            binding.rvFullScreenImages.scrollToPosition(it)

        }
        fullScreenViewModel.imageListMLD.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                fullScreenImageAdapter.imageList = it
            }
        }
    }

}