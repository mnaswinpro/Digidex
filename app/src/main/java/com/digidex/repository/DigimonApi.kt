package com.digidex.repository

import com.digidex.repository.detail.data.DigimonDetailResponse
import com.digidex.repository.listing.data.DigimonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface DigimonApi {

    @GET("digimon")
    fun getDigimonList(
        @Query("pageSize") pageSize: String = "20",
        @Query("page") page: String = "0"
    ) : Call<DigimonListResponse>?

    @GET
    fun getDigimon(@Url digimonUrl: String) : Call<DigimonDetailResponse>?
}