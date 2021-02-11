package com.diegomfv.moodtrackerv2.app

import android.app.Application
import com.diegomfv.moodtrackerv2.AppProvider
import com.diegomfv.moodtrackerv2.BuildConfig
import com.diegomfv.moodtrackerv2.data.LocalDataSource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber

/**
 * Test application to run UI Tests
 * */
class TestApp : Application() {

    private val sharedPrefDataSource: LocalDataSource by inject()

    override fun onCreate() {
        super.onCreate()
        initTestDI()
        AppProvider.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        GlobalScope.launch {
            sharedPrefDataSource.buildContainerIfDoesNotExist()
        }
    }

}