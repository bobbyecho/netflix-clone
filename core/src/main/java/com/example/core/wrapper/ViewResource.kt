package com.example.core.wrapper

sealed class ViewResource<T>(
    val payload: T? = null,
    val messsage: String? = null,
    val exception: Exception? = null
) {
    class Success<T>(data: T): ViewResource<T>(data)
    class Error<T>(exception: Exception?, data: T? = null): ViewResource<T>(data, exception = exception)
    class Empty<T>(data: T? = null, message: String? = null): ViewResource<T>(data)
    class Loading<T>(data: T? = null): ViewResource<T>(data)
}