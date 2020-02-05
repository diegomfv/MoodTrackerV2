package com.diegomfv.moodtrackerv2.usecase

import com.diegomfv.moodtrackerv2.data.LocalDataSource
import com.diegomfv.moodtrackerv2.data.SharedPrefDataSource

class UpdateStateUsecase(
    private val sharedPrefDataSource: LocalDataSource
) {

    fun invoke(moodState: Int) {
        TODO()
    }

}