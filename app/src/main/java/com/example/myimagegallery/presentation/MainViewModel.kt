package com.example.myimagegallery.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myimagegallery.data.imageaccess.ImageRepositoryImpl
import com.example.myimagegallery.data.model.Image
import com.example.myimagegallery.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val imageRepository: ImageRepository) : ViewModel() {
    private val _images = MutableLiveData<List<Image>>()
    val images: LiveData<List<Image>>
        get() = _images
    private val _album = MutableLiveData<Map<String, List<Image>>>()

    val album: LiveData<Map<String, List<Image>>>
        get() = album

    val fullScreenImage = MutableLiveData<Map<Long, List<Image>>>(null)


    val navigateToFullScreenImage = MutableLiveData(false)
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error


    init {
        getImageData()
        getAlbumData()
    }

    fun getImageData() {
        viewModelScope.launch {
            try {
                val allImages = async { imageRepository.getImages() }
                _images.postValue(allImages.await())

            } catch (ex: Exception) {
                _error.postValue("Something went wrong $error")
            }
        }
    }

    fun getAlbumData() {
        _images.value?.let { images ->
            if (images.isEmpty()) {
                viewModelScope.launch {
                    try {
                        val allImages = async { imageRepository.getImages() }
                        _images.postValue(allImages.await())

                    } catch (ex: Exception) {
                        _error.postValue("Something went wrong $error")
                    }
                }
            } else {
                _album.postValue(imageRepository.getAlbums(images))
            }
        }

    }

    fun imageListItemClick(imageList: List<Image>, imageId: Long) {
        fullScreenImage.value = mapOf(imageId to imageList)
        navigateToFullScreenImage.value = true
    }
}

