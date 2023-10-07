package com.digidex.di

import com.digidex.domain.DigimonTransformer
import com.digidex.domain.DigimonTransformerImpl
import com.digidex.repository.detail.DigimonDetailRepo
import com.digidex.repository.detail.DigimonDetailRepoImpl
import com.digidex.repository.listing.DigimonListingRepo
import com.digidex.repository.listing.DigimonListingRepoImpl
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
    abstract fun transformer(impl: DigimonTransformerImpl): DigimonTransformer
}