package com.digidex.domain.detail

import com.digidex.domain.UseCaseResult
import com.digidex.domain.data.DigimonDetail
import com.digidex.repository.NetworkResult
import com.digidex.ui.detail.DigimonDetailUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * class with [DigimonDetailUseCase] implementation
 */
class DigimonDetailUseCaseImpl @Inject constructor(
    private val detailRepository: DigimonDetailRepo,
    private val transformer: DigimonDetailTransformer
) : DigimonDetailUseCase {

    override suspend fun execute(url: String): Flow<UseCaseResult<DigimonDetail>> = flow {
        detailRepository.execute(url).collect { networkResult ->
            when (networkResult) {
                is NetworkResult.Success -> {
                    emit(UseCaseResult.Success(transformer.getDigimonDetail(networkResult.result)))
                }

                is NetworkResult.Error -> {
                    emit(UseCaseResult.Error(networkResult.exception))
                }
            }
        }
    }
}
