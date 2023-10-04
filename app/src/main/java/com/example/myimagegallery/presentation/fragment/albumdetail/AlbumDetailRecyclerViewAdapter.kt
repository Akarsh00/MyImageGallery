package com.example.myimagegallery.presentation.fragment.albumdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myimagegallery.data.model.IMAGE_DIFF_UTIL
import com.example.myimagegallery.data.model.Image
import com.example.myimagegallery.databinding.RvImageSingleItemBinding
import com.example.myimagegallery.presentation.MainViewModel
import com.example.myimagegallery.presentation.fragment.homeimage.ImageFragmentViewModel
import javax.inject.Inject

class AlbumDetailRecyclerViewAdapter @Inject constructor() :
    ListAdapter<Image, AlbumDetailRecyclerViewAdapter.ItemViewHolder>(IMAGE_DIFF_UTIL) {
    var mainViewModel: ImageFragmentViewModel? = null

    fun initViewModel(viewModel: ImageFragmentViewModel) {
        mainViewModel = viewModel
    }

    inner class ItemViewHolder(private val binding: RvImageSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) {
            binding.viewModel = mainViewModel
            binding.currentList = currentList
            binding.image = image
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvImageSingleItemBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(image = getItem(position))
    }
}

