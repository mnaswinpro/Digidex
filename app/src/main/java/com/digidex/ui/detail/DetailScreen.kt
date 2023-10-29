package com.digidex.ui.detail

/**
 * Wrapper class to hold data for different screen states in [DetailScreen]
 */
sealed class DetailScreen <out T> {

    class Success<T>(val digimonDetail : T) : DetailScreen<T>()

    class Error(val errorMessage: String) : DetailScreen<Nothing>()

    object Loading : DetailScreen<Nothing>()
}
