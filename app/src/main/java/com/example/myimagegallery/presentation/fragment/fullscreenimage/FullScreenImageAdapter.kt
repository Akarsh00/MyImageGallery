package com.example.myimagegallery.presentation.fragment.fullscreenimage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myimagegallery.data.model.Image
import com.example.myimagegallery.databinding.RvImageFullScreenBinding
import javax.inject.Inject

class FullScreenImageAdapter @Inject constructor() :
    RecyclerView.Adapter<FullScreenImageAdapter.ItemViewHolder>() {
    var imageList = listOf<Image>()

    inner class ItemViewHolder(val binding: RvImageFullScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) {
            binding.image = image
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvImageFullScreenBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(imageList.get(position))
}