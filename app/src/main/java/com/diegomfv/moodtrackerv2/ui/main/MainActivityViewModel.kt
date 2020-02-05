package com.diegomfv.moodtrackerv2.ui.main

import androidx.lifecycle.MutableLiveData
import com.diegomfv.moodtrackerv2.ui.common.Event
import com.diegomfv.moodtrackerv2.usecase.SaveNoteUsecase
import com.diegomfv.moodtrackerv2.usecase.UpdateStateUsecase
import com.diegomfv.splendidrecipesmvvm.ui.common.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivityViewModel(
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    val event = MutableLiveData<Event<EventModel>>()

    sealed class EventModel {
        data class UpdateState(val updateState: Int) : EventModel()
        data class SaveNote(val note: String) : EventModel()
        data class ToastMessage(val string: String?) : EventModel()
    }

}