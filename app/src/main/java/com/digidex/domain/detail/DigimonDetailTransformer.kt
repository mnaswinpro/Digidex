package com.digidex.domain.detail

import com.digidex.domain.data.DigimonDetail
import com.digidex.repository.detail.data.DigimonDetailResponse

/**
 * Interface defining method to get [DigimonDetail] from [DigimonDetailResponse]
 */
interface DigimonDetailTransformer {

    fun getDigimonDetail(response: DigimonDetailResponse): DigimonDetail
}
