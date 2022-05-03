package com.obvious.photosgridassignment.domain.useCases

import android.util.Log
import com.obvious.photosgridassignment.application.utils.toDateOrNull
import com.obvious.photosgridassignment.domain.common.DataError
import com.obvious.photosgridassignment.domain.common.DataResult
import com.obvious.photosgridassignment.domain.common.GeneralException
import com.obvious.photosgridassignment.domain.entities.PhotoEntity
import com.obvious.photosgridassignment.domain.repositories.PhotoRepository

/**
 * Use case to fetch the sorted list of photos list
 * (descending order date -> latest first)
 * @param repository [PhotoRepository]
 */
class FetchSortedListOfPhotosUseCase(private val repository: PhotoRepository) {
    suspend operator fun invoke(): DataResult<List<PhotoEntity>, DataError> {
        return when (val dataResultPhotos = repository.fetchListOfPhotos()) {

            //Successfully fetched list of photos
            is DataResult.Success -> {

                //Get list of photos in descending order of their date
                val sortedListPhotos = dataResultPhotos.data.sortedByDescending {
                    it.strDate.toDateOrNull()
                }

                //Return Data Result Success
                DataResult.Success(data = sortedListPhotos)
            }

            //Fetching list failed
            is DataResult.Failure -> {
                Log.e("FetchSortedList","Exception: ${dataResultPhotos.failure}")
                dataResultPhotos
            }

        }
    }
}