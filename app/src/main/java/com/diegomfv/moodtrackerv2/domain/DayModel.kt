package com.diegomfv.moodtrackerv2.domain

/**
 * UI Model
 */
data class DayModel (
    val itemId: String,
    val day: String,
    val mood: Int,
    val comment: String
)