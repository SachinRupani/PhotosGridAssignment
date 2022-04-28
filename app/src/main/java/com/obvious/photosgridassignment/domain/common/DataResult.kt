package com.obvious.photosgridassignment.domain.common

/**
 * Sealed class for checking results
 * either for success
 * or for failure
 */
sealed class DataResult {
    /**
     * Success
     * @param data generic data to be used
     */
    data class Success<T>(val data: T) : DataResult()

    /**
     * Failure
     * @param msg [String] Cause of failure
     * @param cause [Exception]
     */
    data class Failure(val msg: String, val cause: Exception? = null) : DataResult()
}
