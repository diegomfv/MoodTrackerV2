package com.diegomfv.moodtrackerv2.domain

data class DayModel (
    val day: Int,
    val dayAsString: String,
    val mood: Int,
    val comment: String? = null
)