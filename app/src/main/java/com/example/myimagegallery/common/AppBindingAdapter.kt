package com.example.myimagegallery.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.myimagegallery.R

@BindingAdapter("imageURL")
fun image(imageView: ImageView, uri: String) {
    imageView.load(uri)
}