package com.digidex.ui

import androidx.lifecycle.ViewModel
import com.digidex.dispatcher.CoroutineDispatcherApi
import com.digidex.domain.DigimonTransformer
import com.digidex.repository.detail.DigimonDetailRepo
import com.digidex.repository.listing.DigimonListingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DigimonViewModel @Inject constructor(
    private val listingRepository: DigimonListingRepo,
    private val detailRepository: DigimonDetailRepo,
    private val transformer: DigimonTransformer,
    private val dispatcher: CoroutineDispatcherApi
) : ViewModel() {


}