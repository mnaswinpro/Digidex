package com.digidex.domain.listing

import com.digidex.domain.UseCaseResult
import com.digidex.domain.data.Digimon
import com.digidex.repository.NetworkResult
import com.digidex.ui.listing.DigimonListingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * class with [DigimonListingUseCase] implementation
 */
class DigimonListingUseCaseImpl @Inject constructor(
    private val listingRepository: DigimonListingRepo,
    private val transformer: DigimonListingTransformer
) : DigimonListingUseCase {

    override suspend fun execute(): Flow<UseCaseResult<List<Digimon>>> = flow {
        listingRepository.execute().collect { networkResult ->
            when (networkResult) {
                is NetworkResult.Success -> {
                    emit(UseCaseResult.Success(transformer.getDigimonList(networkResult.result)))
                }

                is NetworkResult.Error -> {
                    emit(UseCaseResult.Error(networkResult.exception))
                }
            }
        }
    }
}
