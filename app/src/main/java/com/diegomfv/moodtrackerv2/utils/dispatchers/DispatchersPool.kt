package com.diegomfv.moodtrackerv2.utils.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

/**
 * A wrapper for the different Dispatchers
 * */
interface DispatchersPool {
    val defaultDipatcher: CoroutineDispatcher
    val ioDipatcher: CoroutineDispatcher
    val mainDipatcher: CoroutineDispatcher
    val unconfinedDipatcher: CoroutineDispatcher
}