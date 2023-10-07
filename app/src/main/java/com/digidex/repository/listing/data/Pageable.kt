package com.digidex.repository.listing.data

data class Pageable(
    val currentPage: Int,
    val elementsOnPage: Int,
    val nextPage: String,
    val previousPage: String,
    val totalElements: Int,
    val totalPages: Int
)