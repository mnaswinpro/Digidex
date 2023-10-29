package com.digidex.ui.listing

import com.digidex.domain.UseCaseResult
import com.digidex.domain.data.Digimon
import kotlinx.coroutines.flow.Flow

/**
 * Interface to fetch [UseCaseResult] of list of [Digimon] from useCase layer
 */
interface DigimonListingUseCase {

    suspend fun execute(): Flow<UseCaseResult<List<Digimon>>>
}
