package com.example.myimagegallery.data.imageaccess

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import android.util.Log
import com.example.myimagegallery.data.model.Image
import com.example.myimagegallery.data.repository.ImageRepository
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(val context: Context) : ImageRepository {

    override suspend fun getImages(): MutableList<Image> {
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.BUCKET_ID
        )

        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"
        val images = mutableListOf<Image>()

        context.contentResolver.query(uri, projection, null, null, sortOrder)?.use { cursor ->
            val idColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val bucketNameColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
            val bucketIdColumnIndex =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumnIndex)
                val bucketName = cursor.getString(bucketNameColumnIndex)
                val bucketId = cursor.getString(bucketIdColumnIndex)
                val imageUri = ContentUris.withAppendedId(uri, id)
                images.add(Image(id, bucketName, bucketId, imageUri.toString()))
                Log.d("ImageLog", "getImages: $imageUri")
            }
        }

        return images
    }

    override fun getAlbums(images: List<Image>): Map<String, List<Image>> =
        images.groupBy { it.bucketName }
}