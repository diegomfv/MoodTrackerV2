package com.diegomfv.moodtrackerv2.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diegomfv.moodtrackerv2.R
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by currentScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
