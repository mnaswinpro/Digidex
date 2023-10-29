package com.digidex.repository

/**
 * Custom exceptions with message to be displayed to user
 */
class NoDataException : Exception() {
    override val message: String
        get() = "No data found"
}

class UnknownException : Exception() {
    override val message: String
        get() = "Something went wrong"
}
