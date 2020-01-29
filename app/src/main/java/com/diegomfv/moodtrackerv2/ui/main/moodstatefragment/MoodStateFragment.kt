package com.diegomfv.moodtrackerv2.ui.main.moodstatefragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.diegomfv.moodtrackerv2.R
import com.diegomfv.moodtrackerv2.ui.common.startActivity
import com.diegomfv.moodtrackerv2.ui.history.HistoryActivity
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.fragment_mood.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class MoodStateFragment : Fragment() {

    companion object {
        const val KEY = "key"
        fun newInstance(moodState: Int): MoodStateFragment {
            val bundle = Bundle()
            bundle.putInt(KEY, moodState)
            val fragment = MoodStateFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val qualifier by Delegates.argExtra(KEY)
    private val moodStateFragmentViewModel: MoodStateFragmentViewModel by currentScope.viewModel(
        this,
        qualifier = named(qualifier)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mood, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        iv_go_to_history.clicks()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe {
                activity?.startActivity<HistoryActivity> { }
            }

        iv_add_comment.clicks()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe {
                //TODO Build alert dialog
            }
    }
}




fun <T> Delegates.argExtra(value: T) = ArgExtra(value)

class ArgExtra<T>(var value: T) {

    operator fun getValue(
        thisRef: Any?,
        prop: KProperty<*>
    ): T {
        return value
    }

    operator fun setValue(
        thisRef: Any?,
        prop: KProperty<*>,
        newValue: T
    ) {
        value = newValue
    }
}