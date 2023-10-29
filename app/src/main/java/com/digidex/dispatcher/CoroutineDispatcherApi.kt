package com.digidex.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Interface defining required coroutine dispatchers to be used in application
 */
interface CoroutineDispatcherApi {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}
