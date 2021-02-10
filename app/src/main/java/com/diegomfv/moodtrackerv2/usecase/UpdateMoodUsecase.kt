package com.diegomfv.moodtrackerv2.usecase

import com.diegomfv.moodtrackerv2.data.repository.Repository


class UpdateMoodUsecase(
    private val repository: Repository
) {

    suspend fun invoke(newMoodState: Int) {
        repository.updateDay(newMoodState, null)
    }

}