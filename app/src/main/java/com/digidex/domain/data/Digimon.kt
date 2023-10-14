package com.digidex.domain.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Digimon(
    val id: String,
    val name: String,
    val imageUrl: String,
    val detailUrl: String
) : Parcelable

fun Digimon.toDigimonDetail() : DigimonDetail {
    return DigimonDetail(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}
