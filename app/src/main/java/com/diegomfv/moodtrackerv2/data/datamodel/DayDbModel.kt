package com.diegomfv.moodtrackerv2.data.datamodel

import com.diegomfv.moodtrackerv2.domain.DayModel

/**
 * Model to save records of the mood and comments per each day
 * */
data class DayDbModel(
    val itemId: String,
    val day: String,
    val mood: Int,
    val comment: String
) {
    fun toDayModel(): DayModel {
        return DayModel(itemId, day, mood, comment)
    }
}
