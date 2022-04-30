package com.obvious.photosgridassignment.ui.common

/**
 * UiState class which holds 3 states
 * Success, Failure & Loading
 */
sealed class UiState<out S> {
    data class Success<out S>(val data: S) : UiState<S>()
    data class Failure(val failureMsg: String) : UiState<Nothing>()
    object Loading : UiState<Nothing>()
}