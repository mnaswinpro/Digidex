package com.digidex.di

import com.digidex.repository.DigimonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

/**
 * An dependency injection module to provide [DigimonApi] for making network api calls
 */
@Module
@InstallIn(ViewModelComponent::class)
class DigimonDataModule {

    @Provides
    fun providesDigimonApi(retrofit: Retrofit): DigimonApi {
        return retrofit.create(DigimonApi::class.java)
    }
}
