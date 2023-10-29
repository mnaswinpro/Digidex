package com.digidex.repository.detail

import com.digidex.dispatcher.CoroutineDispatcherApi
import com.digidex.domain.detail.DigimonDetailRepo
import com.digidex.repository.DigimonApi
import com.digidex.repository.NetworkResult
import com.digidex.repository.NoDataException
import com.digidex.repository.UnknownException
import com.digidex.repository.detail.data.DigimonDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.await
import javax.inject.Inject

/**
 * Class with [DigimonDetailRepo] implementation
 */
class DigimonDetailRepoImpl @Inject constructor(
    private val api: DigimonApi,
    private val dispatcher: CoroutineDispatcherApi
) : DigimonDetailRepo {
    override suspend fun execute(url: String): Flow<NetworkResult<DigimonDetailResponse>> {

        return withContext(dispatcher.io) {
            flow {
                try {
                    val result = api.getDigimon(url)?.await()
                    if (result == null) {
                        emit(NetworkResult.Error(NoDataException()))
                    } else {
                        emit(NetworkResult.Success(result))
                    }
                } catch (e: Exception) {
                    emit(NetworkResult.Error(UnknownException()))
                }
            }
        }
    }
}
