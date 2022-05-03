package com.obvious.photosgridassignment.data.repository

import android.app.Application
import com.obvious.photosgridassignment.data.restApiJsonModels.PhotoJsonModel
import com.obvious.photosgridassignment.data.safeApiCall
import com.obvious.photosgridassignment.domain.common.DataError
import com.obvious.photosgridassignment.domain.common.DataResult
import com.obvious.photosgridassignment.domain.entities.PhotoEntity
import com.obvious.photosgridassignment.domain.repositories.PhotoRepository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

/**
 * Repository Implementation of [PhotoRepository]
 */
class PhotoRepositoryImpl @Inject constructor(
    private val moshi: Moshi,
    private val application: Application
) : PhotoRepository {

    /**
     * Implementation of fetching list of photos
     */
    override suspend fun fetchListOfPhotos(): DataResult<List<PhotoEntity>, DataError> {
        return safeApiCall {
            val listType = Types.newParameterizedType(List::class.java, PhotoJsonModel::class.java)
            val adapter: JsonAdapter<List<PhotoJsonModel>?> = moshi.adapter(listType)

            //Open JSON file to read
            val inputStream = application.applicationContext.assets.open("photos.json")

            //Get JSON string from input stream
            val jsonString = inputStream.bufferedReader().use { it.readText() }

            /**
             * Convert JSON string to POJO/Kotlin data class
             * [PhotoJsonModel] -> [PhotoEntity]
             */
            val listPhotos = adapter.fromJson(jsonString)?.map { photoJsonModel ->
                photoJsonModel.toPhotoEntity()
            }

            /**
             * Return Success as List of PhotoEntity
             */
            listPhotos ?: emptyList()
        }

    }
}
