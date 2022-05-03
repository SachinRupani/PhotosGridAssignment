package com.obvious.photosgridassignment.data

import android.util.Log
import com.obvious.photosgridassignment.domain.common.DataError
import com.obvious.photosgridassignment.domain.common.DataResult
import com.obvious.photosgridassignment.domain.common.GeneralException
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

/**
 * Function which handles the call
 * and the exceptions
 * @return [DataResult] onSuccess - <T>, onFailure [GeneralException]
 */
suspend inline fun <T> safeApiCall(
    crossinline body: suspend () -> T
): DataResult<T, DataError> {
    return try {
        val successData = withContext(Dispatchers.IO) {
            body()
        }
        DataResult.Success(data = successData)
    } catch (exception: IOException) {
        /**
         * Return Failure [DataError.IOError]
         */
        Log.e("safeApiCall","IOException: ${exception.message}")
        DataResult.Failure(failure = DataError.IOError)
    } catch (exception: JsonDataException) {
        /**
         * Return Failure [DataError.JsonDataError]
         */
        Log.e("safeApiCall","JsonDataException: ${exception.message}")
        DataResult.Failure(failure = DataError.JsonDataError)
    } catch (exception: Exception) {
        /**
         * Return Failure [DataError.UnknownError]
         */
        Log.e("safeApiCall","Exception: ${exception.message}")
        DataResult.Failure(failure = DataError.UnknownError)
    }
}