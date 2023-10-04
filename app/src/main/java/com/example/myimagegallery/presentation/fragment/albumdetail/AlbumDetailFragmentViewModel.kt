package com.example.myimagegallery.presentation.fragment.albumdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myimagegallery.data.model.Image

class AlbumDetailFragmentViewModel : ViewModel() {
    val albumName = MutableLiveData<String>()
    val albumImageList = MutableLiveData<List<Image>>()

    fun filterAlbumImageFromAllImage(albumMap: Map<String, List<Image>>) {
        val album = albumMap.filter { it.key == albumName.value }
        album.values.forEach { value ->
            albumImageList.postValue(value)
        }
    }


}