package com.obvious.photosgridassignment.domain.repositories

import com.obvious.photosgridassignment.domain.common.DataError
import com.obvious.photosgridassignment.domain.common.DataResult
import com.obvious.photosgridassignment.domain.entities.PhotoEntity

/**
 * Repository contains all the functions
 * related to photos
 */
interface PhotoRepository {

    /**
     * Function to fetch list of photos
     */
    suspend fun fetchListOfPhotos(): DataResult<List<PhotoEntity>, DataError>
}