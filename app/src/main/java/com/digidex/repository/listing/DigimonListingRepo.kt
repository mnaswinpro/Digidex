package com.digidex.repository.listing

import com.digidex.repository.NetworkResult
import com.digidex.repository.listing.data.DigimonListResponse
import kotlinx.coroutines.flow.Flow

interface DigimonListingRepo {

    suspend fun execute() : Flow<NetworkResult<DigimonListResponse>>
}