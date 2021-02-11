package com.diegomfv.moodtrackerv2.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.diegomfv.moodtrackerv2.constants.QUALIFIER_COLOUR_MANAGER
import com.diegomfv.moodtrackerv2.constants.QUALIFIER_IMAGE_MANAGER
import com.diegomfv.moodtrackerv2.data.LocalDataSource
import com.diegomfv.moodtrackerv2.data.SharedPrefDataSource
import com.diegomfv.moodtrackerv2.data.repository.Repository
import com.diegomfv.moodtrackerv2.ui.history.HistoryActivity
import com.diegomfv.moodtrackerv2.ui.history.HistoryActivityViewModel
import com.diegomfv.moodtrackerv2.ui.main.MainActivity
import com.diegomfv.moodtrackerv2.ui.main.MainActivityViewModel
import com.diegomfv.moodtrackerv2.ui.main.comment.CommentDialogFragment
import com.diegomfv.moodtrackerv2.ui.main.comment.CommentDialogFragmentViewModel
import com.diegomfv.moodtrackerv2.ui.main.moodstatefragment.MoodStateFragment
import com.diegomfv.moodtrackerv2.ui.main.moodstatefragment.MoodStateFragmentViewModel
import com.diegomfv.moodtrackerv2.usecase.GetAllDaysUsecase
import com.diegomfv.moodtrackerv2.usecase.SaveCommentUsecase
import com.diegomfv.moodtrackerv2.usecase.UpdateMoodUsecase
import com.diegomfv.moodtrackerv2.utils.BasicColourManager
import com.diegomfv.moodtrackerv2.utils.BasicImageManager
import com.diegomfv.moodtrackerv2.utils.ColourManager
import com.diegomfv.moodtrackerv2.utils.ImageManager
import com.diegomfv.moodtrackerv2.utils.dispatchers.DispatchersPool
import com.diegomfv.moodtrackerv2.utils.dispatchers.DispatchersPoolImpl
import com.diegomfv.moodtrackerv2.utils.localdateprovider.LocalDateManager
import com.diegomfv.moodtrackerv2.utils.localdateprovider.LocalDateManagerImpl
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

fun Application.initTestDI() {
    startKoin {
        androidLogger()
        androidContext(this@initTestDI)
        modules(listOf(appModule, scopesModule))
    }
}

const val TEST_CONFIGURATION_PREFERENCES = "test_config_pref"

private val appModule = module {
    single<CoroutineDispatcher> { Dispatchers.Main }

    single<SharedPreferences> {
        androidApplication().getSharedPreferences(
            TEST_CONFIGURATION_PREFERENCES,
            Context.MODE_PRIVATE
        )
    }
    single<SharedPreferences.Editor> {
        androidApplication().getSharedPreferences(
            TEST_CONFIGURATION_PREFERENCES,
            Context.MODE_PRIVATE
        ).edit()
    }

    single { Gson() }
    single<LocalDateManager> { LocalDateManagerImpl() }
    single { Repository(get()) }
    single<DispatchersPool> { DispatchersPoolImpl() }
    single<LocalDataSource> { SharedPrefDataSource(get(), get(), get(), get(), get()) }

    single<ColourManager>(named(QUALIFIER_COLOUR_MANAGER)) { BasicColourManager(androidContext()) }
    single<ImageManager>(named(QUALIFIER_IMAGE_MANAGER)) { BasicImageManager() }
}

private val scopesModule = module {

    scope(named<MainActivity>()) {
        viewModel { MainActivityViewModel(get()) }
    }

    scope(named<MoodStateFragment>()) {
        viewModel { (moodState: Int) -> MoodStateFragmentViewModel(moodState, get(), get()) }
        scoped { UpdateMoodUsecase(get()) }
    }

    scope(named<CommentDialogFragment>()) {
        viewModel { CommentDialogFragmentViewModel(get(), get()) }
        scoped { SaveCommentUsecase(get()) }
    }

    scope(named<HistoryActivity>()) {
        viewModel { HistoryActivityViewModel(get(), get()) }
        scoped { GetAllDaysUsecase(get()) }
    }
}