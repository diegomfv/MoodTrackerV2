package com.diegomfv.moodtrackerv2.data.repository

import com.diegomfv.moodtrackerv2.data.LocalDataSource
import com.diegomfv.moodtrackerv2.domain.DayModel


class Repository (private val localDataSource: LocalDataSource) {

    suspend fun getAllDays () : List<DayModel> {
        return localDataSource.getAllDays()
            .map { it.toDayModel()}
    }

    suspend fun getDay (itemId: String) : DayModel? {
        return localDataSource.getAllDays()
            .find { it.itemId == itemId }
            ?.toDayModel()
    }

    suspend fun updateDay (mood: Int?, comment: String?) {
        if (mood == null && comment == null) return
        localDataSource.updateOrCreateDay(mood, comment)
    }
}