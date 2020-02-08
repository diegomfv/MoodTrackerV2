package com.diegomfv.moodtrackerv2.ui.main.comment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.Observer
import com.diegomfv.moodtrackerv2.R
import com.diegomfv.moodtrackerv2.ui.common.debouncedClicks
import com.diegomfv.moodtrackerv2.ui.common.shortToast
import com.diegomfv.moodtrackerv2.ui.main.MainActivityViewModel
import com.diegomfv.moodtrackerv2.utils.DialogSizeManager
import com.diegomfv.moodtrackerv2.utils.Utils
import kotlinx.android.synthetic.main.dialog_fragment_comment.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class CommentDialogFragment : AppCompatDialogFragment() {

    companion object {

        const val KEY = "KEY"

        fun newInstance(): CommentDialogFragment {
            return CommentDialogFragment()
        }
    }

    private val commentDialogFragmentViewModel: CommentDialogFragmentViewModel by currentScope.viewModel(this)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

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

        button_ok.debouncedClicks {
            commentDialogFragmentViewModel.saveComment(comment_edit_text.text.toString())
        }

        button_cancel.debouncedClicks {
            commentDialogFragmentViewModel.dismissByCancel()
        }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(DialogSizeManager.getDialogWidth(), DialogSizeManager.getDialogHeight())
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