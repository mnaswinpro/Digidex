package com.digidex.domain

import com.digidex.repository.detail.data.DigimonDetailResponse
import com.digidex.repository.detail.data.Evolution
import com.digidex.repository.listing.data.DigimonListResponse
import com.digidex.ui.data.Digimon
import com.digidex.ui.data.DigimonDetail
import javax.inject.Inject

class DigimonTransformerImpl @Inject constructor() : DigimonTransformer{

    override fun getDigimonList(response: DigimonListResponse): List<Digimon> {
        return response.content.map {
            Digimon(
                id = "#${it.id}",
                name = it.name,
                imageUrl = it.image,
                detailUrl = it.href
            )
        }
    }

    override fun getDigimonDetail(response: DigimonDetailResponse): DigimonDetail {
        return DigimonDetail(
            id = "#${response.id}",
            name = response.name,
            imageUrl = response.images.first().href,
            description = response.descriptions.first().description,
            priorEvolutions = response.priorEvolutions.map { it.toDigimon() },
            nextEvolutions = response.nextEvolutions.map { it.toDigimon() }
        )
    }

    private fun Evolution.toDigimon() : Digimon {
        return Digimon(
            id = "#${id}",
            name = digimon,
            imageUrl = image,
            detailUrl = url
        )
    }
}