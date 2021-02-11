package com.diegomfv.moodtrackerv2

import android.app.Application

object AppProvider {

    lateinit var app: Application
        private set

    fun init(app: Application) {
        this.app = app
    }

}