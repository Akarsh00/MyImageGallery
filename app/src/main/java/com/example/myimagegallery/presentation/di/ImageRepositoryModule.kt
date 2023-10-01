package com.example.myimagegallery.presentation.di

import com.example.myimagegallery.data.imageaccess.ImageRepositoryImpl
import com.example.myimagegallery.data.repository.ImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class ImageRepositoryModule {

    @Binds
    abstract fun getImageRepository(imageRepositoryImpl: ImageRepositoryImpl): ImageRepository
}