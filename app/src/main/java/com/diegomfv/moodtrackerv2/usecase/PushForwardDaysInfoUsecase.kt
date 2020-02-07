package com.diegomfv.moodtrackerv2.usecase

import com.diegomfv.moodtrackerv2.data.LocalDataSource

class PushForwardDaysInfoUsecase (
    private val localDataSource: LocalDataSource
) {

    suspend fun invoke (amountOfDays: Int) {
        localDataSource.pushForwardDaysInfo(amountOfDays)
    }
}
