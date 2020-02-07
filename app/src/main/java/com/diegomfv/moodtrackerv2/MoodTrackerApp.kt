package com.diegomfv.moodtrackerv2

import android.app.Application
import com.diegomfv.moodtrackerv2.data.LocalDataSource
import com.diegomfv.moodtrackerv2.usecase.CreateAllDaysUsecase
import com.diegomfv.moodtrackerv2.usecase.PushForwardDaysInfoUsecase
import com.diegomfv.moodtrackerv2.usecase.SaveLastSessionUsecase
import com.diegomfv.moodtrackerv2.utils.DaysAheadDetector
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber

class MoodTrackerApp : Application() {

    private val sharedPrefDataSource: LocalDataSource by inject()
    private val createAllDaysUsecase: CreateAllDaysUsecase by inject()
    private val saveLastSessionUsecase: SaveLastSessionUsecase by inject()
    private val pushForwardDaysInfoUsecase: PushForwardDaysInfoUsecase by inject()
    private val daysAheadDetector: DaysAheadDetector by inject()

    override fun onCreate() {
        super.onCreate()
        initDI()
        AppProvider.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        GlobalScope.launch {

            createAllDaysUsecase.invoke()
            saveLastSessionUsecase.invoke()

            if (daysAheadDetector.resolveDaysAhead() > 0) {
                pushForwardDaysInfoUsecase.invoke(daysAheadDetector.resolveDaysAhead())
            }

            if (BuildConfig.DEBUG) {
                sharedPrefDataSource.printAllDays()
            }
        }
    }
}