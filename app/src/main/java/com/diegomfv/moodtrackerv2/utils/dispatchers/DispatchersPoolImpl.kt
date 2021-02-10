package com.diegomfv.moodtrackerv2.utils.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


class DispatchersPoolImpl : DispatchersPool {
    override val defaultDipatcher: CoroutineDispatcher = Dispatchers.Default
    override val ioDipatcher: CoroutineDispatcher = Dispatchers.IO
    override val mainDipatcher: CoroutineDispatcher = Dispatchers.Main
    override val unconfinedDipatcher: CoroutineDispatcher = Dispatchers.Unconfined
}