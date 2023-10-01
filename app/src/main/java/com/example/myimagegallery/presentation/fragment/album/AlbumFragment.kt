package com.example.myimagegallery.presentation.fragment.album

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myimagegallery.R
import com.example.myimagegallery.databinding.FragmentAlbumBinding
import com.example.myimagegallery.databinding.FragmentImageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumFragment : Fragment(R.layout.fragment_album) {
    lateinit var binding: FragmentAlbumBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumBinding.bind(view)
    }
}