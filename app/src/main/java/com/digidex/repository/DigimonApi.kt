package com.digidex.repository

import com.digidex.repository.detail.data.DigimonDetailResponse
import com.digidex.repository.listing.data.DigimonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Interface defining the api endpoints to fetch response from network calls
 */
interface DigimonApi {

    @GET("digimon")
    fun getDigimonList(
        @Query(QUERY_PAGE_SIZE) pageSize: String = DEFAULT_PAGE_SIZE,
        @Query(QUERY_PAGE) page: String = DEFAULT_PAGE
    ): Call<DigimonListResponse>?

    @GET
    fun getDigimon(@Url digimonUrl: String): Call<DigimonDetailResponse>?

    companion object {
        const val QUERY_PAGE_SIZE = "pageSize"
        const val QUERY_PAGE = "page"
        const val DEFAULT_PAGE_SIZE = "20"
        const val DEFAULT_PAGE = "0"
    }
}
