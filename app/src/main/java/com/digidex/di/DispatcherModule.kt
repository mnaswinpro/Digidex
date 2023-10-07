package com.digidex.di

import com.digidex.dispatcher.CoroutineDispatcherApi
import com.digidex.dispatcher.CoroutineDispatcherApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DispatcherModule {

    @Provides
    @Singleton
    fun providesCoroutineDispatcher(): CoroutineDispatcherApi {
        return CoroutineDispatcherApiImpl()
    }
}