package com.digidex.domain.listing

import com.digidex.repository.detail.data.DigimonDetailResponse
import com.digidex.repository.listing.data.DigimonListResponse
import com.digidex.domain.data.Digimon
import com.digidex.domain.data.DigimonDetail

interface DigimonListingTransformer {

    fun getDigimonList(response: DigimonListResponse) : List<Digimon>
}
