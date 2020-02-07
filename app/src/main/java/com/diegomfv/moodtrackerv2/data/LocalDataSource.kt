package com.diegomfv.moodtrackerv2.data

import com.diegomfv.moodtrackerv2.domain.DayModel

interface LocalDataSource {
    suspend fun createAllDays()
    suspend fun printAllDays()

    suspend fun pushForwardDaysInfo (amountOfDays: Int)

    suspend fun getDay(day: Int): DayModel
    suspend fun clearLastDay()
    suspend fun getAllDayModels(): List<DayModel>
    suspend fun updateDayComment(newComment: String)
    suspend fun updateDayState(newMoodState: Int)

    suspend fun getLastSessionMillis() : Long
    suspend fun saveLastSessionLocalDateTime()
}