package com.diegomfv.moodtrackerv2.usecase

import com.diegomfv.moodtrackerv2.data.repository.Repository
import com.diegomfv.moodtrackerv2.domain.DayModel

class GetAllDaysUsecase(
    private val repository: Repository
) {

    suspend fun invoke (): List<DayModel> {
        return repository.getAllDays()
    }

}