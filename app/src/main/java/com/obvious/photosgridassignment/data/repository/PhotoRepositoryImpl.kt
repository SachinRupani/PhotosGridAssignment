package com.obvious.photosgridassignment.data.repository

import android.content.Context
import com.obvious.photosgridassignment.R
import com.obvious.photosgridassignment.data.restApiJsonModels.PhotoJsonModel
import com.obvious.photosgridassignment.domain.common.DataResult
import com.obvious.photosgridassignment.domain.common.GeneralException
import com.obvious.photosgridassignment.domain.entities.PhotoEntity
import com.obvious.photosgridassignment.domain.repositories.PhotoRepository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.IOException
import javax.inject.Inject

/**
 * Repository Implementation of [PhotoRepository]
 */
class PhotoRepositoryImpl @Inject constructor(
    private val moshi: Moshi,
    private val context: Context
) : PhotoRepository {

    /**
     * Implementation of fetching list of photos
     */
    override suspend fun fetchListOfPhotos(): DataResult<List<PhotoEntity>, GeneralException> {
        val listType = Types.newParameterizedType(List::class.java, PhotoJsonModel::class.java)
        val adapter: JsonAdapter<List<PhotoJsonModel>?> = moshi.adapter(listType)

        return try {
            val inputStream = context.assets.open("photos.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }

            val listPhotos = adapter.fromJson(jsonString)?.map { photoJsonModel ->
                photoJsonModel.toPhotoEntity()
            }

            /**
             * Return Success Data Result with data as List of PhotoEntity
             */
            DataResult.Success(data = listPhotos ?: emptyList())
        } catch (exception: IOException) {

            /**
             * Return Failure with string message and exception
             */
            DataResult.Failure(
                failure = GeneralException(
                    message = context.getString(R.string.err_msg_unable_to_fetch_photos),
                    exception = exception
                )
            )
        }


    }

}