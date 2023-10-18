package com.digidex.ui.testUtil

import com.digidex.domain.data.Digimon

fun getDigimonList(): List<Digimon> {

    return listOf(
        Digimon(
            id = "#1",
            name = "Garummon",
            imageUrl = "https://www.digi-api.com/images/digimon/w/Garummon.png",
            detailUrl = "https://www.digi-api.com/api/v1/digimon/1"
        ),
        Digimon(
            id = "#2",
            name = "Craniummon",
            imageUrl = "https://www.digi-api.com/images/digimon/w/Craniummon.png",
            detailUrl = "https://www.digi-api.com/api/v1/digimon/2"
        )
    )
}