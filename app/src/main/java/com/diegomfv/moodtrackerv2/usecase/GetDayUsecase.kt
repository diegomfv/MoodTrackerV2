package com.diegomfv.moodtrackerv2.usecase

import com.diegomfv.moodtrackerv2.data.repository.Repository
import com.diegomfv.moodtrackerv2.domain.DayModel

class GetDayUsecase(
    private val repository: Repository
) {

    suspend fun invoke (itemId: String) : DayModel? {
        return repository.getDay(itemId)
    }

}