package com.digidex.domain.detail

import com.digidex.domain.data.Digimon
import com.digidex.domain.data.DigimonDetail
import com.digidex.repository.detail.data.DigimonDetailResponse
import com.digidex.repository.detail.data.Evolution
import javax.inject.Inject

/**
 * class with [DigimonDetailTransformer] implementation
 */
class DigimonDetailTransformerImpl @Inject constructor() : DigimonDetailTransformer {

    override fun getDigimonDetail(response: DigimonDetailResponse): DigimonDetail {
        return DigimonDetail(
            id = "#${response.id}",
            name = response.name,
            imageUrl = response.images.first().href,
            description = response.descriptions.firstOrNull { it.language == SUPPORTED_LANGUAGE }?.description.orEmpty(),
            priorEvolutions = response.priorEvolutions.map { it.toDigimon() },
            nextEvolutions = response.nextEvolutions.map { it.toDigimon() }
        )
    }

    private fun Evolution.toDigimon(): Digimon {
        return Digimon(
            id = "#${id}",
            name = digimon,
            imageUrl = image,
            detailUrl = url
        )
    }

    companion object {
        const val SUPPORTED_LANGUAGE = "en_us"
    }
}
