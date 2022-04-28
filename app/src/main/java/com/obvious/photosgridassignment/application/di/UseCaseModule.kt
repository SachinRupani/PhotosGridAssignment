package com.obvious.photosgridassignment.application.di

import com.obvious.photosgridassignment.domain.repositories.PhotoRepository
import com.obvious.photosgridassignment.domain.useCases.FetchPhotosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideFetchPhotosUseCase(repository: PhotoRepository): FetchPhotosUseCase {
        return FetchPhotosUseCase(repository = repository)
    }
}