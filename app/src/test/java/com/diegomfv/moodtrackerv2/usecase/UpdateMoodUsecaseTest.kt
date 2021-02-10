package com.diegomfv.moodtrackerv2.usecase

import android.content.SharedPreferences
import com.diegomfv.moodtrackerv2.data.LocalDataSource
import com.diegomfv.moodtrackerv2.data.SharedPrefDataSource
import com.diegomfv.moodtrackerv2.data.datamodel.DayDbModel
import com.diegomfv.moodtrackerv2.data.datamodel.DbContainer
import com.diegomfv.moodtrackerv2.data.repository.Repository
import com.diegomfv.moodtrackerv2.shared.DispatchersPoolTest
import com.diegomfv.moodtrackerv2.utils.localdateprovider.LocalDateManagerImpl
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class UpdateMoodUsecaseTest {

    private val gson: Gson = Gson()

    private val localDataSource : LocalDataSource = SharedPrefDataSource(
        SharedPreferencesTest(),
        EditorTest(),
        LocalDateManagerImpl(),
        DispatchersPoolTest(),
        gson
    )

    private val repository : Repository = Repository(localDataSource)
    private val usecase: UpdateMoodUsecase = UpdateMoodUsecase(repository)

    @Test
    fun `invoke when the days exists updates the mood` () {
        /* Insert model */
        val container = DbContainer(listOf(
            DayDbModel("id0", LocalDateManagerImpl().getTodayAsString(), 0, "c0"),
            DayDbModel("id1", "1", 1, "c1")
        ))

        map.put("data_key", gson.toJson(container))

        /* Run usecase and verify */
        runBlocking {
            usecase.invoke(5)
            val model = gson.fromJson(map.get("data_key"), DbContainer::class.java)
            assertEquals(model.list[0].mood, 5)
        }
    }

    @Test
    fun `invoke when the day does not exist creates the day with the mood` () {
        /* Insert model */
        val container = DbContainer(listOf(
            DayDbModel("id0", "0", 0, "c0"),
            DayDbModel("id1", "1", 1, "c1")
        ))

        map.put("data_key", gson.toJson(container))

        /* Run usecase and verify */
        runBlocking {
            usecase.invoke(5)
            val model = gson.fromJson(map.get("data_key"), DbContainer::class.java)
            assertEquals(model.list.size, 3)
            assertEquals(model.list[0].mood, 0)
            assertEquals(model.list[1].mood, 1)
            assertEquals(model.list[2].mood, 5)
        }
    }

    private val map: MutableMap<String, String> = mutableMapOf()

    inner class SharedPreferencesTest : SharedPreferences {

        override fun getAll(): MutableMap<String, *> {
            TODO("Not yet implemented")
        }

        override fun getString(p0: String?, p1: String?): String? {
            return map[p0]
        }

        override fun getStringSet(p0: String?, p1: MutableSet<String>?): MutableSet<String> {
            TODO("Not yet implemented")
        }

        override fun getInt(p0: String?, p1: Int): Int {
            TODO("Not yet implemented")
        }

        override fun getLong(p0: String?, p1: Long): Long {
            TODO("Not yet implemented")
        }

        override fun getFloat(p0: String?, p1: Float): Float {
            TODO("Not yet implemented")
        }

        override fun getBoolean(p0: String?, p1: Boolean): Boolean {
            TODO("Not yet implemented")
        }

        override fun contains(p0: String?): Boolean {
            TODO("Not yet implemented")
        }

        override fun edit(): SharedPreferences.Editor {
            TODO("Not yet implemented")
        }

        override fun registerOnSharedPreferenceChangeListener(p0: SharedPreferences.OnSharedPreferenceChangeListener?) {
            TODO("Not yet implemented")
        }

        override fun unregisterOnSharedPreferenceChangeListener(p0: SharedPreferences.OnSharedPreferenceChangeListener?) {
            TODO("Not yet implemented")
        }

    }

    inner class EditorTest : SharedPreferences.Editor {

        override fun putString(p0: String?, p1: String?): SharedPreferences.Editor {
            if (p0 != null && p1 != null) {
                map[p0] = p1
            }
            return this
        }

        override fun putStringSet(p0: String?, p1: MutableSet<String>?): SharedPreferences.Editor {
            TODO("Not yet implemented")
        }

        override fun putInt(p0: String?, p1: Int): SharedPreferences.Editor {
            TODO("Not yet implemented")
        }

        override fun putLong(p0: String?, p1: Long): SharedPreferences.Editor {
            TODO("Not yet implemented")
        }

        override fun putFloat(p0: String?, p1: Float): SharedPreferences.Editor {
            TODO("Not yet implemented")
        }

        override fun putBoolean(p0: String?, p1: Boolean): SharedPreferences.Editor {
            TODO("Not yet implemented")
        }

        override fun remove(p0: String?): SharedPreferences.Editor {
            TODO("Not yet implemented")
        }

        override fun clear(): SharedPreferences.Editor {
            TODO("Not yet implemented")
        }

        override fun commit(): Boolean {
            TODO("Not yet implemented")
        }

        override fun apply() {
            //do nothing
        }
    }
}