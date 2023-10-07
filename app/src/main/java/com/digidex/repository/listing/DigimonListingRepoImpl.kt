package com.digidex.repository.listing

import com.digidex.repository.DigimonApi
import com.digidex.repository.NetworkResult
import com.digidex.repository.listing.data.DigimonListResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class DigimonListingRepoImpl @Inject constructor(private val api: DigimonApi) : DigimonListingRepo {

    override suspend fun execute(): Flow<NetworkResult<DigimonListResponse>> = callbackFlow {
        val callback = object : Callback<DigimonListResponse> {
            override fun onResponse(
                call: Call<DigimonListResponse>,
                response: Response<DigimonListResponse>
            ) {
                val result = response.body()
                if (result == null) {
                    trySend(NetworkResult.DataError)
                } else {
                    trySend(NetworkResult.Success(result))
                }
            }

            override fun onFailure(call: Call<DigimonListResponse>, t: Throwable) {
                trySend(NetworkResult.GenericError(Exception(t)))
            }

        }
        with(api.getDigimonList()) {
            this?.enqueue(callback)
            awaitClose { this?.cancel() }
        }
    }
}