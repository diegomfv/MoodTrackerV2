package com.diegomfv.moodtrackerv2

import androidx.test.espresso.intent.rule.IntentsTestRule
import com.diegomfv.moodtrackerv2.app.TestApp
import com.diegomfv.moodtrackerv2.ui.main.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TestAppTest {

    @get:Rule
    val activityTestRule = IntentsTestRule(MainActivity::class.java, false, false)

    @Test
    fun test_app_is_running() {
        activityTestRule.launchActivity(null)
        val app = activityTestRule.activity.application
        assertEquals(app::class, TestApp::class)
    }


}