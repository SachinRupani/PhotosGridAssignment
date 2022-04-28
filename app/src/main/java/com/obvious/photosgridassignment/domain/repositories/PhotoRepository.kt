package com.obvious.photosgridassignment.domain.repositories

import com.obvious.photosgridassignment.domain.common.DataResult

/**
 * Repository contains all the functions
 * related to photos
 */
interface PhotoRepository {

    /**
     * Function to fetch list of photos
     */
    suspend fun fetchListOfPhotos(): DataResult
}