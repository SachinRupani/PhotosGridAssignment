package com.obvious.photosgridassignment.application.utils

import android.content.res.Resources
import com.obvious.photosgridassignment.R
import com.obvious.photosgridassignment.domain.common.DataError

/**
 * Function to get the relevant string message
 * from string resources
 * based on the [DataError]
 * @param resources [Resources] ref
 * @return [String] string error message
 */
fun DataError.resolveErrorMessage(resources: Resources): String {
    return when (this) {
        is DataError.IOError -> resources.getString(R.string.err_msg_io_error)
        is DataError.JsonDataError -> resources.getString(R.string.err_msg_json_data_error)
        is DataError.UnknownError -> resources.getString(R.string.err_msg_unknown_error)
    }
}