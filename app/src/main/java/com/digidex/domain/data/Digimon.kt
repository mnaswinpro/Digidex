package com.digidex.domain.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Digimon(
    val id: String,
    val name: String,
    val imageUrl: String,
    val detailUrl: String
) : Parcelable
