package com.digidex.di

import com.digidex.dispatcher.CoroutineDispatcherApi
import com.digidex.dispatcher.CoroutineDispatcherApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * An dependency injection module to provide coroutine dispatchers required
 */
@InstallIn(SingletonComponent::class)
@Module
abstract class DispatcherModule {

    @Binds
    abstract fun coroutineDispatcher(impl: CoroutineDispatcherApiImpl): CoroutineDispatcherApi
}
