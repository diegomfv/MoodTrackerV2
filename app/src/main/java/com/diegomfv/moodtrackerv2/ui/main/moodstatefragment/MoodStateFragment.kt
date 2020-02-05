package com.diegomfv.moodtrackerv2.ui.main.moodstatefragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.diegomfv.moodtrackerv2.R
import com.diegomfv.moodtrackerv2.ui.common.debouncedClicks
import com.diegomfv.moodtrackerv2.ui.common.setBackgroundColor
import com.diegomfv.moodtrackerv2.ui.common.setImageResource
import com.diegomfv.moodtrackerv2.ui.common.startActivity
import com.diegomfv.moodtrackerv2.ui.history.HistoryActivity
import com.diegomfv.moodtrackerv2.ui.main.MainActivityViewModel
import com.diegomfv.moodtrackerv2.utils.BasicColourManager
import com.diegomfv.moodtrackerv2.utils.BasicImageManager
import kotlinx.android.synthetic.main.fragment_mood.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MoodStateFragment : Fragment() {

    companion object {
        const val KEY = "KEY"
        fun newInstance(moodState: Int): MoodStateFragment {
            return MoodStateFragment().apply {
                arguments = Bundle().apply { putInt(KEY, moodState) }
            }
        }
    }

    private val mainActivityViewModel: MainActivityViewModel by sharedViewModel() //left as example, do not delete

    private val moodStateFragmentViewModel: MoodStateFragmentViewModel by currentScope.viewModel(this) {
        parametersOf(arguments?.getInt(KEY, 2))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mood, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moodStateFragmentViewModel.model.observe(this, Observer(::updateUi))

        iv_mood_state.debouncedClicks {
            moodStateFragmentViewModel.updateState()
        }

        iv_go_to_history.debouncedClicks {
            activity?.startActivity<HistoryActivity> { }
        }

        iv_add_comment.debouncedClicks {
            moodStateFragmentViewModel.saveNote("Note to save") //TODO
        }
    }

    private fun updateUi(uiModel: MoodStateFragmentViewModel.UiModel) {
        when (uiModel) {
            is MoodStateFragmentViewModel.UiModel.Content -> {
                iv_mood_state.setImageResource(BasicImageManager(uiModel.moodState))
                activity?.let { main_container.setBackgroundColor(BasicColourManager(it, uiModel.moodState)) }
            }
        }
    }
}