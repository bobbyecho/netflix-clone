package com.example.core.utils

import android.content.Context
import com.example.core.exception.ApiErrorException
import com.example.core.exception.NoInternetConnectionException

fun Context.getErrorMessageByException(exception: Exception): String {
    return when (exception) {
        is NoInternetConnectionException -> getString(com.example.styling.R.string.message_error_no_internet)
        is ApiErrorException -> exception.message.orEmpty()
        else -> getString(com.example.styling.R.string.message_error_unknown)
    }
}