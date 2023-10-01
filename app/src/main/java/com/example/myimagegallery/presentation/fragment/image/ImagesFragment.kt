package com.example.myimagegallery.presentation.fragment.image

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myimagegallery.R
import com.example.myimagegallery.databinding.FragmentImageBinding
import com.example.myimagegallery.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImagesFragment : Fragment(R.layout.fragment_image) {
    lateinit var binding: FragmentImageBinding
    val viewModel: MainViewModel by activityViewModels()

    @Inject
    lateinit var adapter: ImageRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageBinding.bind(view)
        binding.rvImage.adapter = adapter
        adapter.initViewModel(viewModel)
        viewModel.images.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.submitList(it)
            }
        }

        viewModel.navigateToFullScreenImage.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_imageFragment_to_fullScreenImageFragment)
                viewModel.navigateToFullScreenImage.value = false
            }
        }
    }
}