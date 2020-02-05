package com.diegomfv.moodtrackerv2.data

import com.diegomfv.moodtrackerv2.domain.DayModel

interface LocalDataSource {
    suspend fun createAllDays()
    suspend fun getDay(day: Int): Boolean
    suspend fun clearDay(dayModel: DayModel)
    suspend fun getAllDayModels(): List<DayModel>
    suspend fun updateDayComment(dayModel: DayModel)
    suspend fun updateDayState(dayModel: DayModel)
    suspend fun printAllDays()
}