package com.digidex.domain.listing

import com.digidex.domain.data.Digimon
import com.digidex.repository.listing.data.DigimonListResponse

/**
 * Interface defining method to get list of [Digimon] from [DigimonListResponse]
 */
interface DigimonListingTransformer {

    fun getDigimonList(response: DigimonListResponse): List<Digimon>
}
