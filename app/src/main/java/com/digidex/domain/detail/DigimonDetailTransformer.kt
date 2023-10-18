package com.digidex.domain.detail

import com.digidex.domain.data.DigimonDetail
import com.digidex.repository.detail.data.DigimonDetailResponse

interface DigimonDetailTransformer {

    fun getDigimonDetail(response: DigimonDetailResponse) : DigimonDetail
}
