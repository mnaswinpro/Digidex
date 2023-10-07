package com.digidex.ui.data

sealed class ListingScreen <out T> {

    class Success<T>(val digimonList : T) : ListingScreen<T>()

    class Error(val errorMessage: String) : ListingScreen<Nothing>()

    class Empty(val message: String) : ListingScreen<Nothing>()

    object Loading : ListingScreen<Nothing>()
}
