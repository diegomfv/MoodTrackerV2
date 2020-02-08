package com.diegomfv.moodtrackerv2.usecase

import com.diegomfv.moodtrackerv2.data.LocalDataSource

class UpdateStateUsecase(
    private val localDataSource: LocalDataSource
) {

    suspend fun invoke(newMoodState: Int) {
        localDataSource.updateDayState(newMoodState)
    }

}