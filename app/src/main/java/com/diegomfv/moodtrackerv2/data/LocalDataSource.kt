package com.diegomfv.moodtrackerv2.data

import com.diegomfv.moodtrackerv2.data.datamodel.DayDbModel

/**
 * Contract for all local data sources
 * */
interface LocalDataSource {

    suspend fun buildContainerIfDoesNotExist()
    suspend fun getAllDays(): List<DayDbModel>
    suspend fun getDay(day: Int): DayDbModel?
    suspend fun updateOrCreateDay(mood: Int?, comment: String?)
}