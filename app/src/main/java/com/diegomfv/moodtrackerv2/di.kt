package com.diegomfv.moodtrackerv2

import android.app.Application
import com.diegomfv.moodtrackerv2.ui.history.HistoryActivity
import com.diegomfv.moodtrackerv2.ui.history.HistoryActivityViewModel
import com.diegomfv.moodtrackerv2.ui.main.MainActivity
import com.diegomfv.moodtrackerv2.ui.main.MainActivityViewModel
import com.diegomfv.moodtrackerv2.ui.main.moodstatefragment.MoodStateFragment
import com.diegomfv.moodtrackerv2.usecase.GetDaysUsecase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, usecaseModule, scopesModule))
    }
}

private val appModule = module {
    single<CoroutineDispatcher> { Dispatchers.Main }
}

private val usecaseModule =  module {
    single { GetDaysUsecase() }
}

private val scopesModule = module {

    scope(named<MainActivity>()) {
        viewModel { MainActivityViewModel(get(), get(), get()) }
    }

    scope(named<HistoryActivity>()) {
        viewModel { HistoryActivityViewModel(get(), get()) }
    }
}