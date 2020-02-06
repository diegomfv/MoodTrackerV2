package com.diegomfv.moodtrackerv2.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diegomfv.moodtrackerv2.domain.DayModel
import com.diegomfv.moodtrackerv2.ui.common.Event
import com.diegomfv.moodtrackerv2.usecase.GetDaysUsecase
import com.diegomfv.splendidrecipesmvvm.ui.common.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HistoryActivityViewModel(
    val getDaysUsecase: GetDaysUsecase,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) {
                refreshAdapter()
            }
            return _model
        }

    val event = MutableLiveData<Event<EventModel>>()

    fun refreshAdapter() {
        GlobalScope.launch {
            _model.postValue(UiModel.Loading)

            val dayModels = getDaysUsecase.invoke().filterNotNull() //TODO Necessary, see issue
            _model.postValue(UiModel.Content(dayModels))
        }
    }


    fun onDayClicked(dayModel: DayModel) {
        GlobalScope.launch {
            event.postValue(Event(EventModel.ToastMessage(dayModel.comment)))
        }
    }

    sealed class UiModel {
        object Loading : UiModel()
        data class Content(val dayModelList: List<DayModel>) : UiModel()
        data class Error(val throwable: Throwable) : UiModel()
    }

    sealed class EventModel {
        data class ToastMessage(val string: String?) : EventModel()
    }

}