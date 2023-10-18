package com.digidex.ui

import com.digidex.dispatcher.CoroutineDispatcherApi
import com.digidex.repository.DigimonApi
import com.digidex.repository.NetworkResult
import com.digidex.repository.listing.DigimonListingRepoImpl
import com.digidex.repository.listing.data.DigimonListResponse
import com.digidex.ui.testUtil.loadAsString
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
class DigimonListingRepoTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var mockCoroutineDispatcher: CoroutineDispatcherApi
    private lateinit var mockWebServer: MockWebServer
    private lateinit var mockDigimonApi: DigimonApi

    private lateinit var mockDigimonListingRepo: DigimonListingRepoImpl

    @Before
    fun beforeTest() {
        mockCoroutineDispatcher = mockk()
        every { mockCoroutineDispatcher.io } returns testDispatcher
        mockWebServer = MockWebServer()
        mockDigimonApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DigimonApi::class.java)
        mockDigimonListingRepo = DigimonListingRepoImpl(mockDigimonApi, mockCoroutineDispatcher)
    }

    @After
    fun afterTest() {
        mockWebServer.shutdown()
        clearAllMocks()
    }

    @Test
    fun test() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(loadAsString("digimon_list_response.json"))
        )

        val result = mockDigimonListingRepo.execute()

        result.collect { networkResult ->
            assert(networkResult is NetworkResult.Success<DigimonListResponse>)
            with(networkResult as NetworkResult.Success<DigimonListResponse>) {
                assert(this.result.content.isNotEmpty())
                Assert.assertEquals(this.result.content[0].id, 1)
                Assert.assertEquals(this.result.content[0].name, "Garummon")
                Assert.assertEquals(
                    this.result.content[0].href,
                    "https://www.digi-api.com/api/v1/digimon/1"
                )
                Assert.assertEquals(
                    this.result.content[0].image,
                    "https://www.digi-api.com/images/digimon/w/Garummon.png"
                )
            }
        }
    }
}