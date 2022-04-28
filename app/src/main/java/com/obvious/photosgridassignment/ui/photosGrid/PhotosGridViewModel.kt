package com.obvious.photosgridassignment.ui.photosGrid

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obvious.photosgridassignment.domain.useCases.FetchPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosGridViewModel @Inject constructor(
    private val fetchPhotosUseCase: FetchPhotosUseCase
) : ViewModel() {

    init {
        fetchPhotos()
    }


    private fun fetchPhotos(dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        viewModelScope.launch(dispatcher) {
            val dataResult = fetchPhotosUseCase.invoke()
            Log.d("DataResult",dataResult.toString())
        }
    }

}