package com.diegomfv.moodtrackerv2.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diegomfv.moodtrackerv2.domain.DayModel
import com.diegomfv.moodtrackerv2.ui.common.Event
import com.diegomfv.moodtrackerv2.ui.common.ScopedViewModel
import com.diegomfv.moodtrackerv2.usecase.GetAllDaysUsecase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HistoryActivityViewModel(
    private val getAllDaysUsecase: GetAllDaysUsecase,
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

    private fun refreshAdapter() {
        launch {
            _model.postValue(UiModel.Loading)

            try {
                val dayModels = getAllDaysUsecase.invoke()
                _model.postValue(UiModel.Content(dayModels))

            } catch (e: Exception) {
                e.printStackTrace()
                _model.postValue(UiModel.Error(e))
            }
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