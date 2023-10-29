package com.digidex.domain.listing

import com.digidex.domain.data.Digimon
import com.digidex.repository.listing.data.DigimonListResponse
import javax.inject.Inject

/**
 * class with [DigimonListingTransformer] implementation
 */
class DigimonListingTransformerImpl @Inject constructor() : DigimonListingTransformer {

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
}
