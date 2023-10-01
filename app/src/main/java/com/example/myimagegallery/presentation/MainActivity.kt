package com.example.myimagegallery.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myimagegallery.R
import com.example.myimagegallery.data.imageaccess.ImageRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(IO).launch {
            ImageRepositoryImpl(context = applicationContext).getImages()

        }
    }
}