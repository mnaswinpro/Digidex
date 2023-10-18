package com.digidex.repository

/**
 * A custom exception class when response data is null
 */
class NoDataException : Exception() {
    override val message : String = "No data found"
}
