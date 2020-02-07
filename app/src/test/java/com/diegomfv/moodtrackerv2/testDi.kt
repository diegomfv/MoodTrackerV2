package com.diegomfv.moodtrackerv2

import android.content.Context
import android.content.SharedPreferences
import com.diegomfv.moodtrackerv2.constants.CONFIGURATION_PREFERENCES
import com.diegomfv.moodtrackerv2.data.FakeLocalDataSource
import com.diegomfv.moodtrackerv2.data.LocalDataSource
import com.diegomfv.moodtrackerv2.data.SharedPrefDataSource
import com.diegomfv.moodtrackerv2.usecase.CreateAllDaysUsecase
import com.diegomfv.moodtrackerv2.usecase.PushForwardDaysInfoUsecase
import com.diegomfv.moodtrackerv2.usecase.SaveLastSessionUsecase
import com.diegomfv.moodtrackerv2.utils.*
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun initMockedDi(vararg modules: Module) {
    startKoin {
        modules(listOf(mockedAppModule) + modules)
    }
}

private val mockedAppModule = module {
    single<CoroutineDispatcher> { Dispatchers.Unconfined }

    single<SharedPreferences> { androidApplication().getSharedPreferences(CONFIGURATION_PREFERENCES, Context.MODE_PRIVATE) }
    single<SharedPreferences.Editor> { androidApplication().getSharedPreferences(
        CONFIGURATION_PREFERENCES, Context.MODE_PRIVATE).edit() }

    single<LocalDataSource> { FakeLocalDataSource() }

    single<ColourManager>(named(QUALIFIER_COLOUR_MANAGER)) { BasicColourManager(androidContext()) }
    single<ImageManager>(named(QUALIFIER_IMAGE_MANAGER)) { BasicImageManager() }
}