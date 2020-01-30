package com.diegomfv.moodtrackerv2.ui.main.moodstatefragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.diegomfv.moodtrackerv2.R
import com.diegomfv.moodtrackerv2.ui.common.*
import com.diegomfv.moodtrackerv2.ui.history.HistoryActivity
import com.diegomfv.moodtrackerv2.utils.BasicColourManager
import com.diegomfv.moodtrackerv2.utils.BasicImageManager
import com.diegomfv.moodtrackerv2.utils.argument
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_mood.*
import java.util.concurrent.TimeUnit

class MoodStateFragment : Fragment() {

    companion object {
        fun newInstance(moodState: Int): MoodStateFragment {
            return MoodStateFragment().apply {
                this.moodState = moodState
            }
        }
    }

    private var moodState: Int by argument()

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
        iv_mood_state.setImageResource(BasicImageManager(moodState))
        activity?.let { main_container.setBackgroundColor(BasicColourManager(it, moodState)) }

        iv_mood_state.debouncedClicks {
            activity?.shortToast("State set")
        }

        iv_go_to_history.debouncedClicks {
            activity?.startActivity<HistoryActivity> { }
        }

        iv_add_comment.debouncedClicks {
            activity?.shortToast("Showing dialog")
            //TODO Check if this leaks
            //TODO Build alert dialog
        }
    }
}