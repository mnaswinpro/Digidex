package com.digidex.domain

import com.digidex.repository.detail.data.DigimonDetailResponse
import com.digidex.repository.listing.data.DigimonListResponse
import com.digidex.domain.data.Digimon
import com.digidex.domain.data.DigimonDetail

interface DigimonTransformer {

    fun getDigimonList(response: DigimonListResponse) : List<Digimon>

    fun getDigimonDetail(response: DigimonDetailResponse) : DigimonDetail
}