package com.digidex.repository.detail

import com.digidex.repository.DigimonApi
import com.digidex.repository.NetworkResult
import com.digidex.repository.detail.data.DigimonDetailResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class DigimonDetailRepoImpl @Inject constructor(private val api: DigimonApi) : DigimonDetailRepo {

    override suspend fun execute(url: String): Flow<NetworkResult<DigimonDetailResponse>> = callbackFlow {
        val callback = object : Callback<DigimonDetailResponse> {
            override fun onResponse(
                call: Call<DigimonDetailResponse>,
                response: Response<DigimonDetailResponse>
            ) {
                val result = response.body()
                if (result == null) {
                    trySend(NetworkResult.DataError)
                } else {
                    trySend(NetworkResult.Success(result))
                }
            }

            override fun onFailure(call: Call<DigimonDetailResponse>, t: Throwable) {
                trySend(NetworkResult.GenericError(Exception(t)))
            }

        }
        with(api.getDigimon(url)) {
            this?.enqueue(callback)
            awaitClose { this?.cancel() }
        }
    }
}