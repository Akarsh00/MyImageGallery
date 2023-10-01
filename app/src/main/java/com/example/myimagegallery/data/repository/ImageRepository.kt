package com.example.myimagegallery.data.repository

import com.example.myimagegallery.data.model.Image

interface ImageRepository {
    suspend fun getImages(): MutableList<Image>
    fun getAlbums(images: List<Image>): Map<String, List<Image>>
}