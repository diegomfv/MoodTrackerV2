package com.diegomfv.moodtrackerv2.ui.main.comment

import androidx.lifecycle.MutableLiveData
import com.diegomfv.moodtrackerv2.AppProvider
import com.diegomfv.moodtrackerv2.R
import com.diegomfv.moodtrackerv2.ui.common.Event
import com.diegomfv.moodtrackerv2.usecase.SaveCommentUsecase
import com.diegomfv.splendidrecipesmvvm.ui.common.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CommentDialogFragmentViewModel(
    private val saveCommentUsecase: SaveCommentUsecase,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    val event = MutableLiveData<Event<EventModel>>()

    fun saveComment(comment: String) {
        GlobalScope.launch {
            saveCommentUsecase.invoke(AppProvider.app.getString(R.string.comment_saved).plus(comment))
            event.postValue(Event(EventModel.Dismiss(comment)))
        }
    }

    fun dismissByCancel() {
        GlobalScope.launch {
            event.postValue(Event(EventModel.Dismiss(AppProvider.app.getString(R.string.comment_not_saved))))
        }
    }

    sealed class EventModel {
        data class Dismiss(val message: String) : EventModel()
    }

}