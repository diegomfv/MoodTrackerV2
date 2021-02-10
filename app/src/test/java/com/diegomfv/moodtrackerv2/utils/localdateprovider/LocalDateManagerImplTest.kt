package com.diegomfv.moodtrackerv2.utils.localdateprovider

import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class LocalDateManagerImplTest {

    @Test
    fun `getTodayAsString returns today`() {
        val todayFromMethod = LocalDateManagerImpl().getTodayAsString()
        val today = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault())
            .format(LocalDate.now(ZoneId.systemDefault()))
        assertEquals(todayFromMethod, today)
    }

    @Test
    fun `isSameDayAsToday if same day as today returns true`() {
        val today = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault())
            .format(LocalDate.now(ZoneId.systemDefault()))
        assertTrue(LocalDateManagerImpl().isSameDayAsToday(today))
    }

    @Test
    fun `isSameDayAsToday if different day than today returns true`() {
        val today = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault())
            .format(LocalDate.now(ZoneId.systemDefault()).plusDays(1))
        assertFalse(LocalDateManagerImpl().isSameDayAsToday(today))
    }
}