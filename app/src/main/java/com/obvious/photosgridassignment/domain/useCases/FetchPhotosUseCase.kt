package com.obvious.photosgridassignment.domain.useCases

import com.obvious.photosgridassignment.domain.common.DataResult
import com.obvious.photosgridassignment.domain.repositories.PhotoRepository

class FetchPhotosUseCase(private val repository: PhotoRepository) {
    suspend operator fun invoke(): DataResult {
        return repository.fetchListOfPhotos()
    }
}