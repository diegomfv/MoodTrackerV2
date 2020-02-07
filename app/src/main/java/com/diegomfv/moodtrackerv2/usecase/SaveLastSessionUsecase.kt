package com.diegomfv.moodtrackerv2.usecase

import com.diegomfv.moodtrackerv2.data.LocalDataSource

class SaveLastSessionUsecase (
    private val localDataSource: LocalDataSource
) {

    suspend fun invoke () {
        localDataSource.saveLastSessionLocalDateTime()
    }

}