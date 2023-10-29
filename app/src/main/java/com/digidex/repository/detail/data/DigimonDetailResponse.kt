package com.digidex.repository.detail.data

/**
 * Data class to hold response from network call for getting digimon detail
 */
data class DigimonDetailResponse(
    val attributes: List<Attribute>,
    val descriptions: List<Description>,
    val fields: List<Field>,
    val id: Int,
    val images: List<Image>,
    val levels: List<Level>,
    val name: String,
    val nextEvolutions: List<Evolution>,
    val priorEvolutions: List<Evolution>,
    val releaseDate: String,
    val skills: List<Skill>,
    val types: List<Type>,
    val xAntibody: Boolean
)