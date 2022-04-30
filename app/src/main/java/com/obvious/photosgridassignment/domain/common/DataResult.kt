package com.obvious.photosgridassignment.domain.common

/**
 * Sealed class for checking results
 * either for success
 * or for failure
 */
sealed class DataResult<out S, out F> {
    /**
     * Success
     * @param data generic data to be used
     */
    data class Success<out S>(val data: S) : DataResult<S, Nothing>()

    /**
     * Failure
     * @param failure Cause of failure
     */
    data class Failure<out F>(val failure: F) : DataResult<Nothing, F>()
}
