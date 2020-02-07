package com.diegomfv.moodtrackerv2.data

import com.diegomfv.moodtrackerv2.constants.listOfDaysAsString
import com.diegomfv.moodtrackerv2.domain.DayModel
import kotlin.random.Random

class FakeLocalDataSource : LocalDataSource {

    override suspend fun createAllDays() {
        println("Created")
    }

    override suspend fun printAllDays() {
        println("Printed")
    }

    override suspend fun pushForwardDaysInfo(amountOfDays: Int) {
        println("Pushed forward $amountOfDays")
    }

    override suspend fun getDay(day: Int): DayModel {
        return DayModel.emptyDay(day)
    }

    override suspend fun clearLastDay() {
        println("Cleared")
    }

    override suspend fun getAllDayModels(): List<DayModel> {
        return listOfDaysAsString
            .indices
            .toList()
            .fold (mutableListOf()) { acc, n ->
                acc.apply { add(DayModel.emptyDay(n).copy(dayAsString = listOfDaysAsString[n], mood = Random.nextInt(0, 5), comment = "")) }
            }
    }

    override suspend fun updateDayComment(newComment: String) {
        println("Updated: $newComment")
    }

    override suspend fun updateDayState(newMoodState: Int) {
        println("Updated: $newMoodState")
    }

    override suspend fun getLastSessionMillis(): Long {
        return Random.nextLong()
    }

    override suspend fun saveLastSessionLocalDateTime() {
        println("Saved")
    }
}