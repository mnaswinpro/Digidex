package com.digidex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digidex.dispatcher.CoroutineDispatcherApi
import com.digidex.domain.DigimonTransformer
import com.digidex.domain.data.Digimon
import com.digidex.domain.data.DigimonDetail
import com.digidex.repository.NetworkResult
import com.digidex.repository.detail.DigimonDetailRepo
import com.digidex.repository.listing.DigimonListingRepo
import com.digidex.ui.detail.DetailScreen
import com.digidex.ui.listing.ListingScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DigimonViewModel @Inject constructor(
    private val listingRepository: DigimonListingRepo,
    private val detailRepository: DigimonDetailRepo,
    private val transformer: DigimonTransformer,
    private val dispatcher: CoroutineDispatcherApi
) : ViewModel() {

    private val _digimonListLiveData =
        MutableLiveData<ListingScreen<List<Digimon>>>(ListingScreen.Loading)
    val digimonListLiveData: LiveData<ListingScreen<List<Digimon>>> get() = _digimonListLiveData

    private val _digimonDetailLiveData =
        MutableLiveData<DetailScreen<DigimonDetail>>(DetailScreen.Loading)
    val digimonDetailLiveData: LiveData<DetailScreen<DigimonDetail>> get() = _digimonDetailLiveData

    fun fetchDigimonList() {
        viewModelScope.launch(dispatcher.io) {
            listingRepository.execute().collect {
                when (it) {
                    is NetworkResult.Success -> {
                        if (it.result.content.isEmpty()) {
                            _digimonListLiveData.postValue(
                                ListingScreen.Empty(
                                    ERROR_MESSAGE_EMPTY_DATA
                                )
                            )
                        } else {
                            _digimonListLiveData.postValue(
                                ListingScreen.Success(transformer.getDigimonList(it.result))
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _digimonListLiveData.postValue(ListingScreen.Error(ERROR_MESSAGE_NULL_DATA))
                    }
                }
            }
        }
    }

    fun fetchDigimon(url: String) {
        viewModelScope.launch(dispatcher.io) {
            detailRepository.execute(url).collect {
                when (it) {
                    is NetworkResult.Success -> {
                        _digimonDetailLiveData.postValue(
                            DetailScreen.Success(transformer.getDigimonDetail(it.result))
                        )
                    }

                    is NetworkResult.Error -> {
                        _digimonDetailLiveData.postValue(DetailScreen.Error(ERROR_MESSAGE_NULL_DATA))
                    }
                }
            }
        }
    }

    companion object {
        const val ERROR_MESSAGE_NULL_DATA = "No Data found"
        const val ERROR_MESSAGE_EMPTY_DATA = "No Monsters in list"
        const val ERROR_MESSAGE_GENERIC = "Something went wrong"
    }
}