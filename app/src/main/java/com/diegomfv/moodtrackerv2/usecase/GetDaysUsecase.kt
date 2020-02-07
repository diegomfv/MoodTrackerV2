package com.diegomfv.moodtrackerv2.usecase

import com.diegomfv.moodtrackerv2.data.LocalDataSource
import com.diegomfv.moodtrackerv2.domain.DayModel

class GetDaysUsecase (
    private val localDataSource: LocalDataSource
) {

    suspend fun invoke () : List<DayModel> {
        return localDataSource.getAllDayModels()
    }

}