package com.diegomfv.moodtrackerv2.utils.localdateprovider

interface LocalDateManager {

    fun getTodayAsString () : String
    fun isSameDayAsToday(date: String): Boolean
}