package com.digidex.domain.listing

import com.digidex.repository.listing.data.DigimonListResponse
import com.digidex.domain.data.Digimon
import javax.inject.Inject

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

