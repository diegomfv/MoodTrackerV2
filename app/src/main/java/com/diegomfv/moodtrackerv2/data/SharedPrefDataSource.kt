package com.diegomfv.moodtrackerv2.data

import android.content.SharedPreferences
import com.diegomfv.moodtrackerv2.constants.LAST_SESSION_TIME_MILLIS
import com.diegomfv.moodtrackerv2.constants.MOOD_NORMAL
import com.diegomfv.moodtrackerv2.constants.listOfDaysAsString
import com.diegomfv.moodtrackerv2.domain.DayModel
import com.diegomfv.moodtrackerv2.utils.DateTimeManager
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SharedPrefDataSource(
    private val sharedPreferences: SharedPreferences,
    private val sharedPreferencesEditor: SharedPreferences.Editor,
    private val gson: Gson
) : LocalDataSource {

    val DEFAULT_VALUE_STRING = ""

    override suspend fun createAllDays() {
        return withContext(Dispatchers.IO) {
            val dbIsEmpty =
                sharedPreferences.getString(0.toString(), DEFAULT_VALUE_STRING).isNullOrBlank()
            if (dbIsEmpty) {
                listOfDaysAsString.indices.forEach {
                    sharedPreferencesEditor.putString(
                        it.toString(),
                        gson.toJson(DayModel(it, listOfDaysAsString[it], MOOD_NORMAL, ""))
                    )
                }
                sharedPreferencesEditor.apply()
            }
        }
    }

    override suspend fun getDay(day: Int): DayModel {
        return withContext(Dispatchers.IO) {
            val today = sharedPreferences.getString(day.toString(), DEFAULT_VALUE_STRING)
            gson.fromJson(today, DayModel::class.java)
        }
    }

    override suspend fun clearLastDay() {
        return withContext(Dispatchers.IO) {
            val lastDayIndex = listOfDaysAsString.lastIndex
            sharedPreferencesEditor.putString(
                lastDayIndex.toString(),
                gson.toJson(DayModel.emptyDay(lastDayIndex))
            )
            sharedPreferencesEditor.apply()
        }
    }

    override suspend fun getAllDayModels(): List<DayModel> {
        return withContext(Dispatchers.IO) {
            listOfDaysAsString.indices.map {
                val day = sharedPreferences.getString(it.toString(), DEFAULT_VALUE_STRING)
                gson.fromJson(day, DayModel::class.java)
            }
        }
    }

    override suspend fun updateDayComment(newComment: String) {
        return withContext(Dispatchers.IO) {
            val today = sharedPreferences.getString(0.toString(), DEFAULT_VALUE_STRING)
            val dayModel = gson.fromJson(today, DayModel::class.java)
            sharedPreferencesEditor.putString(
                0.toString(),
                gson.toJson(dayModel.copy(comment = newComment))
            )
            sharedPreferencesEditor.apply()
        }
    }

    override suspend fun updateDayState(newMoodState: Int) {
        return withContext(Dispatchers.IO) {
            val today = sharedPreferences.getString(0.toString(), DEFAULT_VALUE_STRING)
            val dayModel = gson.fromJson(today, DayModel::class.java)
            sharedPreferencesEditor.putString(
                0.toString(),
                gson.toJson(dayModel.copy(mood = newMoodState))
            )
            sharedPreferencesEditor.apply()
        }
    }

    override suspend fun printAllDays() {
        return withContext(Dispatchers.IO) {
            listOfDaysAsString.indices.forEach {
                val day = sharedPreferences.getString(it.toString(), DEFAULT_VALUE_STRING)
                println(day)
            }
        }
    }

    //TODO Should be in Unit of Work
    override suspend fun pushForwardDaysInfo(amountOfDays: Int) {
        return withContext(Dispatchers.IO) {
            val allDayModels = getAllDayModels()
            for (i in allDayModels.size - 1 downTo 0) {

                //TODO Separate this logic
                when {
                    i == allDayModels.size ->  {
                        //do nothing
                    }
                    i > amountOfDays -> {
                        val day = getDay(i)
                        sharedPreferencesEditor.putString((i+1).toString(), gson.toJson(day))
                    }
                    else -> {
                        sharedPreferencesEditor.putString(i.toString(), gson.toJson(DayModel.emptyDay(i)))
                    }
                }
            }
        }
    }

    override suspend fun getLastSessionMillis(): Long {
        return withContext(Dispatchers.IO) {
            sharedPreferences.getLong(LAST_SESSION_TIME_MILLIS, Long.MAX_VALUE)
        }
    }

    override suspend fun saveLastSessionLocalDateTime() {
        return withContext(Dispatchers.IO) {
            sharedPreferencesEditor.putLong(LAST_SESSION_TIME_MILLIS, DateTimeManager.getCurrentTimeInMillis()) //TODO Inject via DI with abstraction
            sharedPreferencesEditor.apply()
        }
    }

}