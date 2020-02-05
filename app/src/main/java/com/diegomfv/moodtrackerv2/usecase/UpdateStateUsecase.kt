package com.diegomfv.moodtrackerv2.usecase

import com.diegomfv.moodtrackerv2.data.MoodStateModel
import com.diegomfv.moodtrackerv2.data.SharedPrefDataSource

class UpdateStateUsecase(
    private val sharedPrefDataSource: SharedPrefDataSource
) {

    fun invoke(moodState: Int) {
        TODO()
    }

}