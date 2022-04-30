package com.obvious.photosgridassignment.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.obvious.photosgridassignment.domain.common.DataResult
import com.obvious.photosgridassignment.domain.entities.PhotoEntity
import com.obvious.photosgridassignment.domain.useCases.FetchSortedListOfPhotosUseCase
import com.obvious.photosgridassignment.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val fetchSortedListOfPhotosUseCase: FetchSortedListOfPhotosUseCase
) : ViewModel() {

    //Live data holding list of photos
    val uiStatePhotos: LiveData<UiState<List<PhotoEntity>>> =
        liveData(viewModelScope.coroutineContext) {
            val dataResult = withContext(Dispatchers.IO) {
                fetchSortedListOfPhotosUseCase.invoke()
            }
            when (dataResult) {
                is DataResult.Success -> {
                    emit(UiState.Success(data = dataResult.data))
                }
                is DataResult.Failure -> emit(UiState.Failure(failureMsg = dataResult.failure.message))
            }
        }

}