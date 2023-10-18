package com.digidex.domain

import java.lang.Exception

sealed class UseCaseResult<out T> {
    class Success<T>(val result : T) : UseCaseResult<T>()
    class Error(val exception: Exception) : UseCaseResult<Nothing>()
}
