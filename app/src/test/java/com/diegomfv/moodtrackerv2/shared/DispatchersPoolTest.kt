package com.diegomfv.moodtrackerv2.shared

import com.diegomfv.moodtrackerv2.utils.dispatchers.DispatchersPool
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatchersPoolTest: DispatchersPool {
    override val defaultDipatcher: CoroutineDispatcher = Dispatchers.Unconfined
    override val ioDipatcher: CoroutineDispatcher = Dispatchers.Unconfined
    override val mainDipatcher: CoroutineDispatcher = Dispatchers.Unconfined
    override val unconfinedDipatcher: CoroutineDispatcher = Dispatchers.Unconfined
}