package com.diegomfv.moodtrackerv2.ui.main.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.Observer
import com.diegomfv.moodtrackerv2.R
import com.diegomfv.moodtrackerv2.extensions.getScreenOrientation
import com.diegomfv.moodtrackerv2.extensions.shortToast
import com.diegomfv.moodtrackerv2.extensions.throttleFirst
import com.diegomfv.moodtrackerv2.utils.DialogSizeManager
import kotlinx.android.synthetic.main.dialog_fragment_comment.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class CommentDialogFragment : AppCompatDialogFragment() {

    companion object {

        fun newInstance(): CommentDialogFragment {
            return CommentDialogFragment()
        }
    }

    private val commentDialogFragmentViewModel: CommentDialogFragmentViewModel by currentScope.viewModel(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_comment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        commentDialogFragmentViewModel.event.observe(this, Observer { it.getContentIfNotHandled()?.let { triggerEvent(it) } })

        button_ok_background?.throttleFirst {
            commentDialogFragmentViewModel.saveComment(comment_edit_text.text.toString())
        }

        button_cancel_background?.throttleFirst {
            commentDialogFragmentViewModel.dismissByCancel()
        }
    }

    override fun onResume() {
        super.onResume()
        DialogSizeManager.setDialogMeasure(dialog?.window, activity?.getScreenOrientation())
    }

    private fun triggerEvent (event: CommentDialogFragmentViewModel.EventModel) {
        when (event) {
            is CommentDialogFragmentViewModel.EventModel.Dismiss -> {
                activity?.shortToast(event.message)
                dismiss()
            }
        }
    }
}