package com.example.myimagegallery.presentation.fragment.fullscreenimage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myimagegallery.data.model.Image

class FullScreenViewModel : ViewModel() {
    val imageListMLD = MutableLiveData<List<Image>>()
    val positionToScrollMLD = MutableLiveData<Int>()
    fun initialize(item: Map<Long, List<Image>>) {
        if (item.isEmpty()) {
            return
        }
        val key = item.keys.first()
        val imageList = item[key] ?: mutableListOf()
        val image = imageList.first { it.id == key }
        val index = imageList.indexOf(image)
        imageListMLD.value = imageList
        positionToScrollMLD.value = index
    }
}