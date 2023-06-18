package com.example.getmoview.common

sealed class Resources<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resources<T>(data)
    class Error<T>(data: T?, message: String?) : Resources<T>(data, message)
    class IsLoading<T>(data: T? = null) : Resources<T>(data)
}
