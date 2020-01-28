package com.diegomfv.moodtrackerv2

import android.app.Application
import timber.log.Timber

class MoodTrackerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
        AppProvider.init(this)
        if (BuildConfig.DEBUG) { Timber.plant(Timber.DebugTree()) }
    }
}