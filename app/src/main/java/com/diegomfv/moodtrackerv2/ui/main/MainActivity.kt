package com.diegomfv.moodtrackerv2.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.diegomfv.moodtrackerv2.R
import com.diegomfv.moodtrackerv2.ui.common.shortToast
import com.diegomfv.moodtrackerv2.ui.common.startActivity
import com.diegomfv.moodtrackerv2.ui.history.HistoryActivity
import com.diegomfv.moodtrackerv2.ui.history.HistoryActivityViewModel
import com.diegomfv.moodtrackerv2.ui.main.moodstatefragment.MoodStateFragment
import com.diegomfv.moodtrackerv2.utils.AlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by currentScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel.event.observe(this, Observer { it.getContentIfNotHandled()?.let { triggerEvent(it) } })

        val listOfChildFragments = arrayListOf<Fragment>(
            MoodStateFragment.newInstance(0),
            MoodStateFragment.newInstance(1),
            MoodStateFragment.newInstance(2),
            MoodStateFragment.newInstance(3),
            MoodStateFragment.newInstance(4)
        )

        val adapter = ViewPagerAdapter(
            this,
            listOfFragments = listOfChildFragments
        )

        view_pager.offscreenPageLimit = 4 //TODO Find a proper solution
        view_pager.adapter = adapter

    }

    private fun triggerEvent (event: MainActivityViewModel.EventModel) {
        when (event) {
            is MainActivityViewModel.EventModel.UpdateState -> {

            }
            is MainActivityViewModel.EventModel.SaveNote -> {

            }
            is MainActivityViewModel.EventModel.ToastMessage -> {
                shortToast(event.string ?: "Toast is null")
            }
        }
    }
}
