package com.example.myimagegallery.presentation.fragment.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myimagegallery.R
import com.example.myimagegallery.data.model.Image
import com.example.myimagegallery.databinding.AlbumItemBinding
import javax.inject.Inject

class AlbumRecyclerViewAdapter @Inject constructor() : RecyclerView.Adapter<AlbumRecyclerViewAdapter.AlbumViewHolder>() {

    var itemsAlbum: List<Image> = mutableListOf()

    inner class AlbumViewHolder(val binding: AlbumItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemsAlbum: Image) {

            binding.image = itemsAlbum
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AlbumItemBinding.inflate(inflater, parent, false)
        return AlbumViewHolder(binding)
    }

    override fun getItemCount(): Int = itemsAlbum.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(itemsAlbum[position])

    }
}
