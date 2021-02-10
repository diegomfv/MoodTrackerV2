package com.diegomfv.moodtrackerv2.data

import android.content.SharedPreferences
import com.diegomfv.moodtrackerv2.data.datamodel.DayDbModel
import com.diegomfv.moodtrackerv2.data.datamodel.DbContainer
import com.diegomfv.moodtrackerv2.shared.DispatchersPoolTest
import com.diegomfv.moodtrackerv2.utils.localdateprovider.LocalDateManagerImpl
import com.google.gson.Gson
import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SharedPrefDataSourceTest {

    private val gson: Gson = Gson()
    private val editor: SharedPreferences.Editor = EditorTest()

    private val localDataSource: LocalDataSource = SharedPrefDataSource(
        SharedPreferencesTest(),
        editor,
        LocalDateManagerImpl(),
        DispatchersPoolTest(),
        gson
    )

    @Test
    fun `buildContainerIfDoesNotExist inserts the container if it does not exist`() {
        runBlocking {
            /* Verify there is no data in SharedPref file*/
            assertTrue(map.isEmpty())

            /* Run usecase */
            localDataSource.buildContainerIfDoesNotExist()

            /* Verify insertion */
            assertTrue(map.isNotEmpty())
        }
    }

    @Test
    fun `buildContainerIfDoesNotExist does not insert the container if it does exist`() {
        val spy = spyk<SharedPreferences.Editor>(editor)
        every { spy.putString("data_key", any()).apply() } returns Unit

        runBlocking {
            /* Verify there is no data in SharedPref file*/
            assertTrue(map.isEmpty())

            /* Run usecase */
            localDataSource.buildContainerIfDoesNotExist()

            /* Verify no insertion */
            verify(exactly = 0) { spy.putString("data_key", any()).apply() }
        }
    }

    @Test
    fun `getAllDays returns all days`() {
        runBlocking {
            /* Verify there is no data in SharedPref file*/
            assertTrue(map.isEmpty())

            /* Add some data */
            map.put(
                "data_key", gson.toJson(
                    DbContainer(
                        listOf(
                            DayDbModel("id0", "0", 0, "c0"),
                            DayDbModel("id1", "1", 0, "c1")
                        )
                    )
                )
            )

            /* Run usecase */
            val result = localDataSource.getAllDays()

            /* Verify insertion */
            assertEquals(result.size, 2)
            assertEquals(result[0].itemId, "id0")
            assertEquals(result[1].itemId, "id1")
        }
    }

    @Test
    fun `updateOrCreateDay updates the day if it exists`() {
        runBlocking {
            /* Verify there is no data in SharedPref file*/
            assertTrue(map.isEmpty())

            /* Add some data */
            map.put(
                "data_key", gson.toJson(
                    DbContainer(
                        listOf(
                            DayDbModel("id0", LocalDateManagerImpl().getTodayAsString(), 0, "c0"),
                            DayDbModel("id1", "1", 1, "c1")
                        )
                    )
                )
            )

            /* Run usecase */
            localDataSource.updateOrCreateDay(5, "newComment")

            /* Get days */
            val result = localDataSource.getAllDays()

            /* Verify insertion */
            assertEquals(result.size, 2)
            assertEquals(result[0].mood, 5)
            assertEquals(result[0].comment, "newComment")
            assertEquals(result[1].mood, 1)
            assertEquals(result[1].comment, "c1")
        }
    }

    @Test
    fun `updateOrCreateDay creates the day if it does not exist`() {
        runBlocking {
            /* Verify there is no data in SharedPref file*/
            assertTrue(map.isEmpty())

            /* Add some data */
            map.put(
                "data_key", gson.toJson(
                    DbContainer(
                        listOf(
                            DayDbModel("id0", "0", 0, "c0"),
                            DayDbModel("id1", "1", 1, "c1")
                        )
                    )
                )
            )

            /* Run usecase */
            localDataSource.updateOrCreateDay(5, "newComment")

            /* Get days */
            val result = localDataSource.getAllDays()

            /* Verify insertion */
            assertEquals(result.size, 3)
            assertEquals(result[0].mood, 0)
            assertEquals(result[0].comment, "c0")
            assertEquals(result[1].mood, 1)
            assertEquals(result[1].comment, "c1")
            assertEquals(result[2].mood, 5)
            assertEquals(result[2].comment, "newComment")
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