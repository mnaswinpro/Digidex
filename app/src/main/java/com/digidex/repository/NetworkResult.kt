package com.digidex.repository

import java.lang.Exception

/**
 * Wrapper Class to hold data when Success or Error result is returned from repository layer
 */
sealed class NetworkResult<out T> {

    class Success<T>(val result : T) : NetworkResult<T>()

    class Error(val exception: Exception) : NetworkResult<Nothing>()
}
