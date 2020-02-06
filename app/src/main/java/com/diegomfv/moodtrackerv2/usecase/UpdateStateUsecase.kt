package com.diegomfv.moodtrackerv2.usecase

import com.diegomfv.moodtrackerv2.data.LocalDataSource

class UpdateStateUsecase(
    private val sharedPrefDataSource: LocalDataSource
) {

    suspend fun invoke(newMoodState: Int) {
        sharedPrefDataSource.updateDayState(newMoodState)
    }

}