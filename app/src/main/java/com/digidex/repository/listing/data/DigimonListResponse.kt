package com.digidex.repository.listing.data

/**
 * Data class to hold response from network call for getting digimon listing
 */
data class DigimonListResponse(
    val content: List<DigimonResponse>,
    val pageable: Pageable
)