package com.digidex.domain.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Data class to hold basic Digimon info
 */
@Parcelize
data class Digimon(
    val id: String,
    val name: String,
    val imageUrl: String,
    val detailUrl: String
) : Parcelable
