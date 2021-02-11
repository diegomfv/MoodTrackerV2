package com.diegomfv.moodtrackerv2.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.diegomfv.moodtrackerv2.R
import com.diegomfv.moodtrackerv2.constants.*
import com.diegomfv.moodtrackerv2.extensions.shortToast
import com.diegomfv.moodtrackerv2.ui.main.moodstatefragment.MoodStateFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by currentScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listOfChildFragments = arrayListOf<Fragment>(
            MoodStateFragment.newInstance(MOOD_VERY_HAPPY),
            MoodStateFragment.newInstance(MOOD_HAPPY),
            MoodStateFragment.newInstance(MOOD_NORMAL),
            MoodStateFragment.newInstance(MOOD_DISAPPOINTED),
            MoodStateFragment.newInstance(MOOD_SAD)
        )

        val adapter = ViewPagerAdapter(
            this,
            listOfFragments = listOfChildFragments
        )

        view_pager.offscreenPageLimit = 1
        view_pager.adapter = adapter

    }
}
