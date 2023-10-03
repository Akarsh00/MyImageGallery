package com.example.myimagegallery.presentation.fragment.album

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

    @Inject
    lateinit var albumAdapter: AlbumRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumBinding.bind(view)
        viewModel.album.observe(viewLifecycleOwner) {
            binding.rvAlbum.adapter = albumAdapter
            val listOfImages = mutableListOf<Image>()
            it.entries.forEachIndexed { index, entry ->
                listOfImages.add(entry.value.first())
            }
            binding.rvAlbum.adapter = albumAdapter
            albumAdapter.itemsAlbum = listOfImages
        }
    }
}