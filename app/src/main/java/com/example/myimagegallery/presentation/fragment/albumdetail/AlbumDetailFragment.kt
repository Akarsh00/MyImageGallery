package com.example.myimagegallery.presentation.fragment.albumdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myimagegallery.R
import com.example.myimagegallery.databinding.AlbumDetailFragmentBinding
import com.example.myimagegallery.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumDetailFragment : Fragment(R.layout.album_detail_fragment) {
    lateinit var binding: AlbumDetailFragmentBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val albumDetailViewModel: AlbumDetailFragmentViewModel by activityViewModels()

    @Inject
    lateinit var albumDetailRecyclerViewAdapter: AlbumDetailRecyclerViewAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AlbumDetailFragmentBinding.bind(view)
        arguments?.let {
            albumDetailViewModel.albumName.value = it.getString("bucketName", "")
        }
        mainViewModel.album.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                albumDetailViewModel.filterAlbumImageFromAllImage(it)
            }
        }
        binding.rvAlbumDetail.adapter = albumDetailRecyclerViewAdapter
        albumDetailViewModel.albumImageList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                albumDetailRecyclerViewAdapter.submitList(it)

            }
        }
    }
}