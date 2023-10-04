package com.example.myimagegallery.presentation.fragment.homeimage

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
    private val mainViewModel: MainViewModel by activityViewModels()
    private val imageViewModel: ImageFragmentViewModel by viewModels()

    @Inject
    lateinit var adapter: ImageRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageBinding.bind(view)
        binding.rvImage.adapter = adapter
        adapter.initViewModel(imageViewModel)
        mainViewModel.images.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                imageViewModel.images.value = it
            }
        }
        imageViewModel.images.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.submitList(it)
            }
        }

        imageViewModel.navigateToFullScreenImage.observe(viewLifecycleOwner) {
            if (it) {
                mainViewModel.fullScreenImage.value = imageViewModel.fullScreenImage
                findNavController().navigate(R.id.action_imageFragment_to_fullScreenImageFragment)
                imageViewModel.navigateToFullScreenImage.value = false
            }
        }
    }
}