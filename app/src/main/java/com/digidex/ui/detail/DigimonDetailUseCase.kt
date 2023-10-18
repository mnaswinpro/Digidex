package com.digidex.ui.detail

import com.digidex.domain.UseCaseResult
import com.digidex.domain.data.DigimonDetail
import kotlinx.coroutines.flow.Flow

interface DigimonDetailUseCase {
    suspend fun execute(url: String) : Flow<UseCaseResult<DigimonDetail>>
}
