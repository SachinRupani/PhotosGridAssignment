package com.obvious.photosgridassignment.application.di

import com.obvious.photosgridassignment.data.repository.PhotoRepositoryImpl
import com.obvious.photosgridassignment.domain.repositories.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providePhotoRepository(photoRepository: PhotoRepositoryImpl): PhotoRepository =
        photoRepository
}