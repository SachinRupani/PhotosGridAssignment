package com.obvious.photosgridassignment.domain.common

/**
 * Types of Error
 */
sealed class DataError {
    object IOError : DataError()
    object JsonDataError : DataError()
    object UnknownError : DataError()
}