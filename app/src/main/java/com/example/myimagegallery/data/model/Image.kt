package com.example.myimagegallery.data.model

import android.net.Uri
import androidx.recyclerview.widget.DiffUtil

data class Image(
    val id: Long,
    val bucketName: String,
    val bucketId: String,
    val imageUri: String,
    val isSelected: Boolean = false
)

val IMAGE_DIFF_UTIL = object : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean =
        oldItem.id == newItem.id && oldItem.isSelected == newItem.isSelected

}