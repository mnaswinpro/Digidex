package com.digidex.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatcherApi {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfirmed: CoroutineDispatcher
}