package com.digidex.domain.detail

import com.digidex.repository.NetworkResult
import com.digidex.repository.detail.data.DigimonDetailResponse
import kotlinx.coroutines.flow.Flow

/**
 * Interface to fetch [NetworkResult] of [DigimonDetailResponse] from repository layer
 */
interface DigimonDetailRepo {

    suspend fun execute(url: String): Flow<NetworkResult<DigimonDetailResponse>>
}
