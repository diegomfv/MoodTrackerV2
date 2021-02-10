package com.diegomfv.moodtrackerv2.data

import android.content.SharedPreferences
import com.diegomfv.moodtrackerv2.constants.MOOD_NORMAL
import com.diegomfv.moodtrackerv2.data.datamodel.DayDbModel
import com.diegomfv.moodtrackerv2.data.datamodel.DbContainer
import com.diegomfv.moodtrackerv2.utils.dispatchers.DispatchersPool
import com.diegomfv.moodtrackerv2.utils.localdateprovider.LocalDateManager
import com.google.gson.Gson
import kotlinx.coroutines.withContext
import java.util.*

/**
 * Simple way of storing the data.
 * */
class SharedPrefDataSource(
    private val sharedPreferences: SharedPreferences,
    private val sharedPreferencesEditor: SharedPreferences.Editor,
    private val localDateManager: LocalDateManager,
    private val dispatchersPool : DispatchersPool,
    private val gson: Gson
) : LocalDataSource {

    private val KEY = "data_key"
    private val DEFAULT_VALUE_STRING = ""

    override suspend fun buildContainerIfDoesNotExist() {
        return withContext(dispatchersPool.ioDipatcher) {
            val data = sharedPreferences.getString(KEY, DEFAULT_VALUE_STRING) ?: ""
            if (data.isEmpty()) {
                /* Container does not exist, build */
                sharedPreferencesEditor.putString(KEY, gson.toJson(DbContainer(emptyList()))).apply()
            }
            return@withContext
        }
    }

    override suspend fun getAllDays(): List<DayDbModel> {
        return withContext(dispatchersPool.ioDipatcher) {
            val data = sharedPreferences.getString(KEY, DEFAULT_VALUE_STRING)
            val model = gson.fromJson(data, DbContainer::class.java)
            return@withContext model.list
        }
    }

    override suspend fun getDay(day: Int): DayDbModel? {
        return withContext(dispatchersPool.ioDipatcher) {
            val data = sharedPreferences.getString(KEY, DEFAULT_VALUE_STRING)
            val model = gson.fromJson(data, DbContainer::class.java)
            return@withContext model.list.firstOrNull()
        }
    }

    override suspend fun updateOrCreateDay(mood: Int?, comment: String?) {
        return withContext(dispatchersPool.ioDipatcher) {
            val data = sharedPreferences.getString(KEY, DEFAULT_VALUE_STRING)
            val preContainer = gson.fromJson(data, DbContainer::class.java)
            val today = localDateManager.getTodayAsString()

            val index = preContainer.list.indexOfFirst { it.day == today }
            val newDbDay = if (index == -1) {
                /* Day does not exist. Create */
                DayDbModel(
                    System.currentTimeMillis().toString(), //new itemId
                    today,
                    mood ?: MOOD_NORMAL,
                    comment ?: "")


            } else {
                /* Day exists. Update */
                val dbDay = preContainer.list.get(index)
                dbDay.copy(
                    mood = mood ?: dbDay.mood,
                    comment = comment ?: dbDay.comment
                )
            }
            val newList = preContainer.list.toMutableList().apply { set(index, newDbDay) }
            val newContainer = gson.toJson(DbContainer(newList))
            sharedPreferencesEditor.putString(KEY, newContainer).apply()
            return@withContext
        }
    }
}