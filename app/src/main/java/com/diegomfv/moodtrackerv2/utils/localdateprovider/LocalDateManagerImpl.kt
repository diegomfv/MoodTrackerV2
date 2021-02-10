package com.diegomfv.moodtrackerv2.utils.localdateprovider

import java.text.SimpleDateFormat
import java.util.*

class LocalDateManagerImpl : LocalDateManager {

    override fun getTodayAsString(): String {
        return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
    }

    override fun isSameDayAsToday(date: String) : Boolean {
        val today = getTodayAsString()
        return today == date
    }
}