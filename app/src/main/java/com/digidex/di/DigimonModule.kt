package com.digidex.di

import com.digidex.domain.detail.DigimonDetailTransformer
import com.digidex.domain.detail.DigimonDetailTransformerImpl
import com.digidex.domain.detail.DigimonDetailUseCaseImpl
import com.digidex.domain.listing.DigimonListingTransformer
import com.digidex.domain.listing.DigimonListingTransformerImpl
import com.digidex.domain.listing.DigimonListingUseCaseImpl
import com.digidex.domain.detail.DigimonDetailRepo
import com.digidex.repository.detail.DigimonDetailRepoImpl
import com.digidex.domain.listing.DigimonListingRepo
import com.digidex.repository.listing.DigimonListingRepoImpl
import com.digidex.ui.detail.DigimonDetailUseCase
import com.digidex.ui.listing.DigimonListingUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DigimonModule {

    @Binds
    abstract fun listingRepository(impl: DigimonListingRepoImpl): DigimonListingRepo

    @Binds
    abstract fun detailRepository(impl: DigimonDetailRepoImpl): DigimonDetailRepo

    @Binds
    abstract fun listingUseCase(impl: DigimonListingUseCaseImpl): DigimonListingUseCase

    @Binds
    abstract fun detailUseCase(impl: DigimonDetailUseCaseImpl): DigimonDetailUseCase

    @Binds
    abstract fun listingTransformer(impl: DigimonListingTransformerImpl): DigimonListingTransformer

    @Binds
    abstract fun detailTransformer(impl: DigimonDetailTransformerImpl): DigimonDetailTransformer
}
