package com.example.myimagegallery.data.model

import android.net.Uri

data class Image(
    val id: Long,
    val bucketName: String,
    val bucketId: String,
    val imageUri: Uri,
    val isSelected: Boolean = false
)