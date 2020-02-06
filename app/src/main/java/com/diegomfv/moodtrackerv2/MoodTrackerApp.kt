package com.diegomfv.moodtrackerv2

import android.app.Application
import com.diegomfv.moodtrackerv2.data.LocalDataSource
import com.diegomfv.moodtrackerv2.data.SharedPrefDataSource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.scope.currentScope
import timber.log.Timber

class MoodTrackerApp : Application() {

    private val sharedPrefDataSource : LocalDataSource by inject()

    override fun onCreate() {
        super.onCreate()
        initDI()
        AppProvider.init(this)
        if (BuildConfig.DEBUG) { Timber.plant(Timber.DebugTree()) }
        GlobalScope.launch {
            sharedPrefDataSource.createAllDays()
            if (BuildConfig.DEBUG) { sharedPrefDataSource.printAllDays() }
        }
    }
}