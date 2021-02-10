package com.diegomfv.moodtrackerv2.ui.main.moodstatefragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.diegomfv.moodtrackerv2.R
import com.diegomfv.moodtrackerv2.constants.QUALIFIER_COLOUR_MANAGER
import com.diegomfv.moodtrackerv2.constants.QUALIFIER_IMAGE_MANAGER
import com.diegomfv.moodtrackerv2.extensions.shortToast
import com.diegomfv.moodtrackerv2.extensions.startActivity
import com.diegomfv.moodtrackerv2.extensions.throttleFirst
import com.diegomfv.moodtrackerv2.ui.history.HistoryActivity
import com.diegomfv.moodtrackerv2.ui.main.MainActivityViewModel
import com.diegomfv.moodtrackerv2.ui.main.comment.CommentDialogFragment
import com.diegomfv.moodtrackerv2.utils.ColourManager
import com.diegomfv.moodtrackerv2.utils.ImageManager
import kotlinx.android.synthetic.main.fragment_mood.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

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
    private val moodStateFragmentViewModel: MoodStateFragmentViewModel by currentScope.viewModel(this) { parametersOf(arguments?.getInt(KEY, 2)) }

    private val colourManager: ColourManager by currentScope.inject(
        named(QUALIFIER_COLOUR_MANAGER))

    private val imageManager: ImageManager by currentScope.inject(
        named(QUALIFIER_IMAGE_MANAGER))

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
        moodStateFragmentViewModel.event.observe(this, Observer { it.getContentIfNotHandled()?.let { triggerEvent(it) } })

        iv_mood_state?.throttleFirst {
            moodStateFragmentViewModel.updateState()
        }

        iv_go_to_history?.throttleFirst {
            activity?.startActivity<HistoryActivity> { }
        }

        iv_add_comment?.throttleFirst {
            activity?.let { CommentDialogFragment.newInstance().show(it.supportFragmentManager, "CommentDialogFragment") }
        }
    }

    private fun updateUi(uiModel: MoodStateFragmentViewModel.UiModel) {
        when (uiModel) {
            is MoodStateFragmentViewModel.UiModel.Content -> {
                iv_mood_state.setImageResource(imageManager.getFaceImage(uiModel.moodState))
                activity?.let {
                    main_container.setBackgroundColor(
                        colourManager.getMoodColour(
                            uiModel.moodState
                        )
                    )
                }
            }
        }
    }

    private fun triggerEvent (event: MoodStateFragmentViewModel.EventModel) {
        when (event) {
            is MoodStateFragmentViewModel.EventModel.ToastMessage -> {
                event.string?.let {
                    activity?.shortToast(event.string) //TODO
                }
            }
        }
    }
}