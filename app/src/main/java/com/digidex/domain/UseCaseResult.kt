package com.digidex.domain

/**
 * Wrapper Class to hold data when Success or Error result is returned from useCase layer
 */
sealed class UseCaseResult<out T> {

    class Success<T>(val result: T) : UseCaseResult<T>()

    class Error(val exception: Exception) : UseCaseResult<Nothing>()
}
