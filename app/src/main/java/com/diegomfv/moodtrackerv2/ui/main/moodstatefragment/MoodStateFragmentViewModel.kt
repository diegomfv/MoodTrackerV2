package com.diegomfv.moodtrackerv2.ui.main.moodstatefragment

import com.diegomfv.moodtrackerv2.data.MoodStateModel
import com.diegomfv.moodtrackerv2.usecase.SaveNoteUsecase
import com.diegomfv.moodtrackerv2.usecase.UpdateStateUsecase
import com.diegomfv.splendidrecipesmvvm.ui.common.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MoodStateFragmentViewModel (
    private val moodState: Int,
    private val updateStateUsecase: UpdateStateUsecase,
    private val saveNoteUsecase: SaveNoteUsecase,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {





    //TODO Dummy for the moment
    fun updateState(moodStateModel: MoodStateModel) {
        GlobalScope.launch {
            updateStateUsecase.invoke(moodStateModel)
        }
    }

    fun saveNote (note: String) {
        GlobalScope.launch {
            saveNoteUsecase.invoke(note)
        }
    }

    sealed class EventModel {
        object Navigation : EventModel()
        object ShowAddNoteDialog : EventModel()
    }
}