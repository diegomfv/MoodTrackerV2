package com.diegomfv.moodtrackerv2.domain

import com.diegomfv.moodtrackerv2.constants.MOOD_NORMAL
import com.diegomfv.moodtrackerv2.constants.listOfDaysAsString

data class DayModel (
    val day: Int,
    val dayAsString: String,
    val mood: Int,
    val comment: String? = null
) {

    companion object {
        fun emptyDay (day: Int) = DayModel(day, listOfDaysAsString[day], MOOD_NORMAL, "")
    }

}