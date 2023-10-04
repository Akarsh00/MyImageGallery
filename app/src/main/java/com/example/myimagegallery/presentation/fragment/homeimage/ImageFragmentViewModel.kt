package com.example.myimagegallery.presentation.fragment.homeimage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myimagegallery.data.model.Image

class ImageFragmentViewModel : ViewModel() {
    val images = MutableLiveData<List<Image>>()
    val fullScreenImage = mutableMapOf<Long, List<Image>>()
    val navigateToFullScreenImage = MutableLiveData(false)

    fun homeImageListItemClick(imageList: List<Image>, imageId: Long) {
        fullScreenImage[imageId] = imageList
        navigateToFullScreenImage.value = true
    }


}