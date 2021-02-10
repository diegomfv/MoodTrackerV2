package com.diegomfv.moodtrackerv2.utils.localdateprovider

import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class LocalDateManagerImpl : LocalDateManager {

    override fun getTodayAsString(): String {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault())
            .format(LocalDate.now(ZoneId.systemDefault()))
    }

    override fun isSameDayAsToday(date: String): Boolean {
        val today = getTodayAsString()
        return today == date
    }
}