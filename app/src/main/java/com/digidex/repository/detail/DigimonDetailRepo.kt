package com.digidex.repository.detail

import com.digidex.repository.NetworkResult
import com.digidex.repository.detail.data.DigimonDetailResponse
import kotlinx.coroutines.flow.Flow

interface DigimonDetailRepo {

    suspend fun execute(url: String) : Flow<NetworkResult<DigimonDetailResponse>>
}