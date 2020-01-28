package com.diegomfv.moodtrackerv2.ui.main

import com.diegomfv.moodtrackerv2.data.MoodStateModel
import com.diegomfv.moodtrackerv2.usecase.UpdateStateUseCase
import com.diegomfv.splendidrecipesmvvm.ui.common.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivityViewModel (
    private val updateStateUseCase: UpdateStateUseCase,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {


    //TODO Dummy for the moment
    fun updateState(moodStateModel: MoodStateModel) {
        GlobalScope.launch {
            updateStateUseCase.invoke(moodStateModel)
        }
    }
}