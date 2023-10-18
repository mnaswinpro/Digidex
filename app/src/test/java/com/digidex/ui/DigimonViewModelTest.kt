package com.digidex.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.digidex.dispatcher.CoroutineDispatcherApi
import com.digidex.domain.UseCaseResult
import com.digidex.domain.data.Digimon
import com.digidex.domain.data.DigimonDetail
import com.digidex.domain.detail.DigimonDetailTransformer
import com.digidex.domain.detail.DigimonDetailTransformerImpl
import com.digidex.domain.listing.DigimonListingTransformer
import com.digidex.domain.listing.DigimonListingTransformerImpl
import com.digidex.ui.detail.DetailScreen
import com.digidex.ui.detail.DigimonDetailUseCase
import com.digidex.ui.listing.DigimonListingUseCase
import com.digidex.ui.listing.ListingScreen
import com.digidex.ui.testUtil.getDigimonList
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DigimonViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var mockDigimonListingUseCase: DigimonListingUseCase
    private lateinit var mockDigimonDetailUseCase: DigimonDetailUseCase
    private lateinit var mockCoroutineDispatcher: CoroutineDispatcherApi

    private lateinit var viewModel: DigimonViewModel
    private lateinit var digimonListingTransformer: DigimonListingTransformer
    private lateinit var digimonDetailTransformer: DigimonDetailTransformer

    private lateinit var listingScreenStates: MutableList<ListingScreen<List<Digimon>>>
    private lateinit var detailScreenStates: MutableList<DetailScreen<DigimonDetail>>

    @Before
    fun beforeTest() {
        mockDigimonListingUseCase = mockk()
        mockDigimonDetailUseCase = mockk()
        mockCoroutineDispatcher = mockk()

        digimonListingTransformer = DigimonListingTransformerImpl()
        digimonDetailTransformer = DigimonDetailTransformerImpl()

        every { mockCoroutineDispatcher.main } returns testDispatcher

        viewModel = DigimonViewModel(
            mockDigimonListingUseCase,
            mockDigimonDetailUseCase,
            mockCoroutineDispatcher
        )

        listingScreenStates = mutableListOf()
        detailScreenStates = mutableListOf()

        viewModel.digimonListLiveData.observeForever {
            listingScreenStates.add(it)
        }

        viewModel.digimonDetailLiveData.observeForever {
            detailScreenStates.add(it)
        }
    }

    @After
    fun afterTest() {
        clearAllMocks()
        listingScreenStates.clear()
        detailScreenStates.clear()
    }

    @Test
    fun `fetchDigimonList should update LiveData with success state when data is available`() {
        // When
        val testData = getDigimonList()
        coEvery { mockDigimonListingUseCase.execute() } returns flowOf(
            UseCaseResult.Success(
                testData
            )
        )

        // Action
        viewModel.fetchDigimonList()

        // Verify
        coVerify { mockDigimonListingUseCase.execute() }
        Assert.assertEquals(listingScreenStates[0], ListingScreen.Loading)
        Assert.assertEquals((listingScreenStates[1] as ListingScreen.Success).digimonList.size, 2)
        with((listingScreenStates[1] as ListingScreen.Success).digimonList[0]) {
            Assert.assertEquals(id, "#1")
            Assert.assertEquals(name, "Garummon")
            Assert.assertEquals(imageUrl, "https://www.digi-api.com/images/digimon/w/Garummon.png")
            Assert.assertEquals(detailUrl, "https://www.digi-api.com/api/v1/digimon/1")
        }
    }
}