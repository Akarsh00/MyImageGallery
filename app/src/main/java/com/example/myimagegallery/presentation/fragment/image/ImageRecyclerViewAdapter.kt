package com.example.myimagegallery.presentation.fragment.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myimagegallery.data.model.IMAGE_DIFF_UTIL
import com.example.myimagegallery.data.model.Image
import com.example.myimagegallery.databinding.RvImageSingleItemBinding
import com.example.myimagegallery.presentation.MainViewModel
import javax.inject.Inject

class ImageRecyclerViewAdapter @Inject constructor() :
    ListAdapter<Image, ImageRecyclerViewAdapter.ItemViewHolder>(IMAGE_DIFF_UTIL) {
    var mainViewModel: MainViewModel? = null

    fun initViewModel(viewModel: MainViewModel) {
        mainViewModel = viewModel
    }

    inner class ItemViewHolder(private val binding: RvImageSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) {
            binding.mainViewModel = mainViewModel
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

