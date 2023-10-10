package com.example.myimagegallery.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageURL")
fun image(imageView: ImageView, uri: String) {
    imageView.load(uri )
}