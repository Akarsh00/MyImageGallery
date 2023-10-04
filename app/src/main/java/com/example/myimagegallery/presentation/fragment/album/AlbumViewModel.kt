package com.example.myimagegallery.presentation.fragment.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myimagegallery.data.model.Image

class AlbumViewModel : ViewModel() {
    val albumImageMLD = MutableLiveData<MutableList<Image?>>()
    val navigateToAlbumDetail = MutableLiveData(false)
    val albumClickedNameMLD = MutableLiveData<String>()
    fun fetchImagesFromAlbum(album: Map<String, MutableList<Image>>) {

        val listOfImages = mutableListOf<Image>()
        album.entries.forEachIndexed { index, entry ->
            listOfImages.add(entry.value.first())
        }


        albumImageMLD.postValue(listOfImages.toMutableList())

    }

    fun onAlbumItemClick(albumName: String) {
        albumClickedNameMLD.value = albumName
        navigateToAlbumDetail.value = true
    }


}