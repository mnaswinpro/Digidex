package com.digidex.domain.data

/**
 * Data class to hold detailed Digimon info
 */
data class DigimonDetail(
    val id: String,
    val name: String,
    val imageUrl: String,
    var description: String? = null,
    val priorEvolutions: List<Digimon> = mutableListOf(),
    val nextEvolutions: List<Digimon> = mutableListOf()
)
