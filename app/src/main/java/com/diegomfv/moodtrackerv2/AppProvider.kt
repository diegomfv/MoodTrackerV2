package com.diegomfv.moodtrackerv2

object AppProvider {

    lateinit var app: MoodTrackerApp
        private set

    fun init(app: MoodTrackerApp) {
        this.app = app
    }

}