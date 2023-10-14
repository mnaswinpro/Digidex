package com.digidex.ui.detail

sealed class DetailScreen <out T> {

    class Success<T>(val digimonDetail : T) : DetailScreen<T>()

    class Error(val errorMessage: String) : DetailScreen<Nothing>()

    object Loading : DetailScreen<Nothing>()
}
