package com.digidex.repository.listing.data

data class DigimonListResponse(
    val content: List<DigimonResponse>,
    val pageable: Pageable
)