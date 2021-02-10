package com.diegomfv.moodtrackerv2.ui.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.diegomfv.moodtrackerv2.AppProvider
import com.diegomfv.moodtrackerv2.R
import com.diegomfv.moodtrackerv2.constants.QUALIFIER_COLOUR_MANAGER
import com.diegomfv.moodtrackerv2.extensions.shortToast
import com.diegomfv.moodtrackerv2.utils.ColourManager
import kotlinx.android.synthetic.main.activity_history.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class HistoryActivity : AppCompatActivity() {

    private val historyActivityViewModel: HistoryActivityViewModel by currentScope.viewModel(this)
    lateinit var adapter: HistoryAdapter

    private val colourManager: ColourManager by currentScope.inject(
        named(QUALIFIER_COLOUR_MANAGER))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        adapter = HistoryAdapter (historyActivityViewModel::onDayClicked, colourManager)
        recycler_view.adapter = adapter

        historyActivityViewModel.model.observe(this, Observer(::updateUI))
        historyActivityViewModel.event.observe(this, Observer { it.getContentIfNotHandled()?.let { event -> triggerEvent(event) } })

    }

    private fun updateUI (uiModel: HistoryActivityViewModel.UiModel) {
        when (uiModel) {
            HistoryActivityViewModel.UiModel.Loading -> shortToast(AppProvider.app.getString(R.string.loading)) //TODO Show a proper placeholder
            is HistoryActivityViewModel.UiModel.Content -> adapter.dayModelList = uiModel.dayModelList
            is HistoryActivityViewModel.UiModel.Error -> shortToast(uiModel.throwable.message ?: "Null Error") //TODO Add Error mapper
        }
    }

    private fun triggerEvent (eventModel: HistoryActivityViewModel.EventModel) {
        when (eventModel) {
            is HistoryActivityViewModel.EventModel.ToastMessage -> eventModel.string?.let { shortToast(it) }
        }
    }
}