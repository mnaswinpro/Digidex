package com.digidex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digidex.dispatcher.CoroutineDispatcherApi
import com.digidex.domain.UseCaseResult
import com.digidex.domain.data.Digimon
import com.digidex.domain.data.DigimonDetail
import com.digidex.repository.NoDataException
import com.digidex.ui.detail.DetailScreen
import com.digidex.ui.detail.DigimonDetailUseCase
import com.digidex.ui.listing.DigimonListingUseCase
import com.digidex.ui.listing.ListingScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DigimonViewModel @Inject constructor(
    private val digimonListingUseCase: DigimonListingUseCase,
    private val digimonDetailUseCase: DigimonDetailUseCase,
    private val dispatcher: CoroutineDispatcherApi
) : ViewModel() {

    private val _digimonListLiveData =
        MutableLiveData<ListingScreen<List<Digimon>>>(ListingScreen.Loading)
    val digimonListLiveData: LiveData<ListingScreen<List<Digimon>>> get() = _digimonListLiveData

    private val _digimonDetailLiveData =
        MutableLiveData<DetailScreen<DigimonDetail>>(DetailScreen.Loading)
    val digimonDetailLiveData: LiveData<DetailScreen<DigimonDetail>> get() = _digimonDetailLiveData

    fun fetchDigimonList() {
        viewModelScope.launch(dispatcher.main) {
            digimonListingUseCase.execute().collect {
                when (it) {
                    is UseCaseResult.Success -> {
                        if (it.result.isEmpty()) {
                            _digimonListLiveData.postValue(
                                ListingScreen.Empty(
                                    ERROR_MESSAGE_EMPTY_DATA
                                )
                            )
                        } else {
                            _digimonListLiveData.postValue(
                                ListingScreen.Success(it.result)
                            )
                        }
                    }

                    is UseCaseResult.Error -> {
                        when(it.exception) {
                            is NoDataException -> {
                                _digimonListLiveData.postValue(ListingScreen.Error(it.exception.message))
                            }
                            else -> {
                                _digimonListLiveData.postValue(ListingScreen.Error(ERROR_MESSAGE_GENERIC))
                            }
                        }
                    }
                }
            }
        }
    }

    fun fetchDigimon(url: String) {
        viewModelScope.launch(dispatcher.main) {
            digimonDetailUseCase.execute(url).collect {
                when (it) {
                    is UseCaseResult.Success -> {
                        _digimonDetailLiveData.postValue(
                            DetailScreen.Success(it.result)
                        )
                    }

                    is UseCaseResult.Error -> {
                        when(it.exception) {
                            is NoDataException -> {
                                _digimonDetailLiveData.postValue(DetailScreen.Error(it.exception.message))
                            }
                            else -> {
                                _digimonDetailLiveData.postValue(DetailScreen.Error(ERROR_MESSAGE_GENERIC))
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val ERROR_MESSAGE_EMPTY_DATA = "No Monsters in list"
        const val ERROR_MESSAGE_GENERIC = "Something went wrong"
    }
}