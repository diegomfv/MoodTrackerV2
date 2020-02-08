package com.diegomfv.moodtrackerv2.ui.main.moodstatefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diegomfv.moodtrackerv2.AppProvider
import com.diegomfv.moodtrackerv2.R
import com.diegomfv.moodtrackerv2.ui.common.Event
import com.diegomfv.moodtrackerv2.usecase.UpdateStateUsecase
import com.diegomfv.splendidrecipesmvvm.ui.common.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MoodStateFragmentViewModel(
    private val moodState: Int,
    private val updateStateUsecase: UpdateStateUsecase,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) {
                refresh()
            }
            return _model
        }


    val event = MutableLiveData<Event<EventModel>>()

    fun refresh() {
        _model.value = UiModel.Content(moodState)
    }

    fun updateState() {
        GlobalScope.launch {
            updateStateUsecase.invoke(moodState)
            event.postValue(Event(EventModel.ToastMessage(AppProvider.app.getString(R.string.mood_saved))))
        }
    }

    sealed class UiModel {
        data class Content(val moodState: Int) : UiModel()
        data class Error(val throwable: Throwable) : UiModel()
    }

    sealed class EventModel {
        data class ToastMessage(val string: String?) : EventModel()
    }

}