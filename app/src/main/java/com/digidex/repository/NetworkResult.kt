package com.digidex.repository

import java.lang.Exception

sealed class NetworkResult<out T> {

    class Success<T>(val result : T) : NetworkResult<T>()

    class GenericError(val exception: Exception) : NetworkResult<Nothing>()

    object DataError : NetworkResult<Nothing>()
}