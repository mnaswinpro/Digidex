package com.digidex.repository

import java.lang.Exception

sealed class NetworkResult<out T> {

    class Success<T>(val result : T) : NetworkResult<T>()

    class Error(val exception: Exception) : NetworkResult<Nothing>()
}
