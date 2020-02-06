package com.diegomfv.moodtrackerv2.usecase

import com.diegomfv.moodtrackerv2.data.LocalDataSource
import com.diegomfv.moodtrackerv2.data.Response
import com.diegomfv.moodtrackerv2.data.SharedPrefDataSource
import com.diegomfv.moodtrackerv2.domain.DayModel
import kotlin.random.Random

class GetDaysUsecase (
    private val sharedPrefDataSource: LocalDataSource
) {

    suspend fun invoke () : List<DayModel> {
        return sharedPrefDataSource.getAllDayModels()
    }

}