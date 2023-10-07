package com.digidex.ui.data

data class DigimonDetail(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val priorEvolutions: List<Digimon>,
    val nextEvolutions: List<Digimon>
)
