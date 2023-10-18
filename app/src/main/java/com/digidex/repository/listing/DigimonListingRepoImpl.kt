package com.digidex.repository.listing

import com.digidex.dispatcher.CoroutineDispatcherApi
import com.digidex.domain.listing.DigimonListingRepo
import com.digidex.repository.DigimonApi
import com.digidex.repository.NetworkResult
import com.digidex.repository.NoDataException
import com.digidex.repository.listing.data.DigimonListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.await
import javax.inject.Inject

class DigimonListingRepoImpl @Inject constructor(
    private val api: DigimonApi,
    private val dispatcher: CoroutineDispatcherApi
) : DigimonListingRepo {
    override suspend fun execute(): Flow<NetworkResult<DigimonListResponse>> {

        return withContext(dispatcher.io) {
            flow {
                val result = api.getDigimonList()?.await()
                if (result == null) {
                    emit(NetworkResult.Error(NoDataException()))
                } else {
                    emit(NetworkResult.Success(result))
                }
            }
        }
    }
}