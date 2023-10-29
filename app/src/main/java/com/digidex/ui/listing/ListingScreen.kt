package com.digidex.ui.listing

/**
 * Wrapper class to hold data for different screen states in [ListingScreen]
 */
sealed class ListingScreen <out T> {

    class Success<T>(val digimonList : T) : ListingScreen<T>()

    class Error(val errorMessage: String) : ListingScreen<Nothing>()

    class Empty(val message: String) : ListingScreen<Nothing>()

    object Loading : ListingScreen<Nothing>()
}
