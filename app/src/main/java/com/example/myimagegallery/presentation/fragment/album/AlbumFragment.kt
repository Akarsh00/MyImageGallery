package com.example.myimagegallery.presentation.fragment.album

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myimagegallery.R
import com.example.myimagegallery.data.model.Image
import com.example.myimagegallery.databinding.FragmentAlbumBinding
import com.example.myimagegallery.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AlbumFragment : Fragment(R.layout.fragment_album) {
    lateinit var binding: FragmentAlbumBinding
    val viewModel: MainViewModel by activityViewModels()
    val albumViewModel: AlbumViewModel by viewModels()

    @Inject
    lateinit var albumAdapter: AlbumRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumBinding.bind(view)
        albumAdapter.initAlbumViewMode(albumViewModel)
        viewModel.album.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                albumViewModel.fetchImagesFromAlbum(it as Map<String, MutableList<Image>>)
            }
        }

        albumViewModel.albumImageMLD.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.rvAlbum.adapter = albumAdapter
                albumAdapter.itemsAlbum = it.toMutableList()

            }
        }
        albumViewModel.navigateToAlbumDetail.observe(viewLifecycleOwner) { it ->
            if (it) {
                findNavController().navigate(R.id.albumDetailFragment, Bundle().apply {
                    putString(
                        "bucketName",
                        "" + albumViewModel.albumClickedNameMLD.value
                    )
                })
                albumViewModel.navigateToAlbumDetail.value = false
            }
        }
    }
}