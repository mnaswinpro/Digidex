package com.digidex.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Class with [CoroutineDispatcherApi] implementation
 */
class CoroutineDispatcherApiImpl @Inject constructor() : CoroutineDispatcherApi {
    override val main: CoroutineDispatcher by lazy { Dispatchers.Main }
    override val io: CoroutineDispatcher by lazy { Dispatchers.IO }
    override val default: CoroutineDispatcher by lazy { Dispatchers.Default }
}
