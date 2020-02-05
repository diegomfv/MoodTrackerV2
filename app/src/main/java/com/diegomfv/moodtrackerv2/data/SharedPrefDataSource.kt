package com.diegomfv.moodtrackerv2.data

import android.app.Application
import android.content.SharedPreferences
import com.diegomfv.moodtrackerv2.constants.MOOD_NORMAL
import com.diegomfv.moodtrackerv2.constants.listOfDaysAsString
import com.diegomfv.moodtrackerv2.domain.DayModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SharedPrefDataSource(
    private val app: Application,
    private val sharedPreferences: SharedPreferences,
    private val sharedPreferencesEditor: SharedPreferences.Editor,
    private val gson: Gson
) : LocalDataSource {

    val DEFAULT_VALUE = Int.MIN_VALUE

    override suspend fun createAllDays() {
        return withContext(Dispatchers.IO) {
            val dbIsAlreadyFilled = sharedPreferences.getInt(1.toString(), DEFAULT_VALUE) != DEFAULT_VALUE
            if (!dbIsAlreadyFilled) {
                (0..14).forEach {
                    sharedPreferencesEditor.putString(it.toString(), gson.toJson(DayModel(it, listOfDaysAsString[it], MOOD_NORMAL, "")))
                }
                sharedPreferencesEditor.apply()
            }
        }
    }

    override suspend fun getDay(day: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun clearDay(dayModel: DayModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAllDayModels(): List<DayModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateDayComment(dayModel: DayModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateDayState(dayModel: DayModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}