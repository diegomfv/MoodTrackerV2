package com.diegomfv.moodtrackerv2

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.diegomfv.moodtrackerv2.constants.CONFIGURATION_PREFERENCES
import com.diegomfv.moodtrackerv2.data.LocalDataSource
import com.diegomfv.moodtrackerv2.data.SharedPrefDataSource
import com.diegomfv.moodtrackerv2.ui.history.HistoryActivity
import com.diegomfv.moodtrackerv2.ui.history.HistoryActivityViewModel
import com.diegomfv.moodtrackerv2.ui.main.MainActivity
import com.diegomfv.moodtrackerv2.ui.main.MainActivityViewModel
import com.diegomfv.moodtrackerv2.ui.main.comment.CommentDialogFragment
import com.diegomfv.moodtrackerv2.ui.main.comment.CommentDialogFragmentViewModel
import com.diegomfv.moodtrackerv2.ui.main.moodstatefragment.MoodStateFragment
import com.diegomfv.moodtrackerv2.ui.main.moodstatefragment.MoodStateFragmentViewModel
import com.diegomfv.moodtrackerv2.usecase.*
import com.diegomfv.moodtrackerv2.utils.*
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
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
        modules(listOf(appModule, scopesModule))
    }
}

private val appModule = module {
    single<CoroutineDispatcher> { Dispatchers.Main }

    single<SharedPreferences> {
        androidApplication().getSharedPreferences(
            CONFIGURATION_PREFERENCES,
            Context.MODE_PRIVATE
        )
    }
    single<SharedPreferences.Editor> {
        androidApplication().getSharedPreferences(
            CONFIGURATION_PREFERENCES,
            Context.MODE_PRIVATE
        ).edit()
    }

    single<LocalDataSource> { SharedPrefDataSource(get(), get(), get()) }

    single { CreateAllDaysUsecase(get()) }
    single { PushForwardDaysInfoUsecase(get()) }
    single { SaveLastSessionUsecase(get()) }

    single { DaysAheadDetector(get()) }
    single { Gson() }

    single<ColourManager>(named(QUALIFIER_COLOUR_MANAGER)) { BasicColourManager(androidContext()) }
    single<ImageManager>(named(QUALIFIER_IMAGE_MANAGER)) { BasicImageManager() }
}

private val scopesModule = module {

    scope(named<MainActivity>()) {
        viewModel { MainActivityViewModel(get()) }
    }

    scope(named<MoodStateFragment>()) {
        viewModel { (moodState: Int) -> MoodStateFragmentViewModel(moodState, get(), get()) }
        scoped { UpdateStateUsecase(get()) }
    }

    scope(named<CommentDialogFragment>()) {
        viewModel { CommentDialogFragmentViewModel(get(), get()) }
        scoped { SaveCommentUsecase(get()) }

    }

    scope(named<HistoryActivity>()) {
        viewModel { HistoryActivityViewModel(get(), get()) }
        scoped { GetDaysUsecase(get()) }
    }

}

//TODO Move from here
const val QUALIFIER_COLOUR_MANAGER = "Q_CM_Basic"
const val QUALIFIER_IMAGE_MANAGER = "Q_IM_Basic"