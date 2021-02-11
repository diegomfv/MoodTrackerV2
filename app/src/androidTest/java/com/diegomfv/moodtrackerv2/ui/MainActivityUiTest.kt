package com.diegomfv.moodtrackerv2.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import com.diegomfv.moodtrackerv2.R
import com.diegomfv.moodtrackerv2.ui.history.HistoryActivity
import com.diegomfv.moodtrackerv2.ui.main.MainActivity
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test

/**
 * TODO Migrate to ActivityScenario
 * */
class MainActivityUiTest {

    @get:Rule
    val activityTestRule = IntentsTestRule(MainActivity::class.java, false, false)

    @Test
    fun super_happy_fragment_is_loaded_first() {
        activityTestRule.launchActivity(null)
        onView(
            allOf(
                withResourceName("iv_mood_state"),
                withTagValue(equalTo(R.drawable.smiley_super_happy))
            )
        )
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun happy_fragment_is_loaded_on_1_swipe_left() {
        activityTestRule.launchActivity(null)

        onView(withId(R.id.view_pager)).perform(swipeLeft());

        onView(
            allOf(
                withResourceName("iv_mood_state"),
                withTagValue(equalTo(R.drawable.smiley_happy))
            )
        ).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun normal_fragment_is_loaded_on_2_swipe_left() {
        activityTestRule.launchActivity(null)

        repeat(2) { onView(withId(R.id.view_pager)).perform(swipeLeft()) }

        onView(
            allOf(
                withResourceName("iv_mood_state"),
                withTagValue(equalTo(R.drawable.smiley_normal))
            )
        ).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun disappointed_fragment_is_loaded_on_3_swipe_left() {
        activityTestRule.launchActivity(null)

        repeat(3) { onView(withId(R.id.view_pager)).perform(swipeLeft()) }

        onView(
            allOf(
                withResourceName("iv_mood_state"),
                withTagValue(equalTo(R.drawable.smiley_disappointed))
            )
        ).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun sad_fragment_is_loaded_on_4_swipe_left() {
        activityTestRule.launchActivity(null)

        repeat(4) { onView(withId(R.id.view_pager)).perform(swipeLeft()) }

        onView(
            allOf(
                withResourceName("iv_mood_state"),
                withTagValue(equalTo(R.drawable.smiley_sad))
            )
        ).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun clicking_smiley_shows_toast_mood_saved() {
        activityTestRule.launchActivity(null)

        onView(
            allOf(
                withResourceName("iv_mood_state"),
                withTagValue(equalTo(R.drawable.smiley_super_happy))
            )
        ).perform(click())
        onView(withText("Mood saved"))
            .inRoot(withDecorView(not(activityTestRule.activity.window.decorView)))
            .check(ViewAssertions.matches(isDisplayed()))


        onView(withId(R.id.view_pager)).perform(swipeLeft())

        onView(
            allOf(
                withResourceName("iv_mood_state"),
                withTagValue(equalTo(R.drawable.smiley_happy))
            )
        ).perform(click())
        onView(withText("Mood saved"))
            .inRoot(withDecorView(not(activityTestRule.activity.window.decorView)))
            .check(ViewAssertions.matches(isDisplayed()))


        onView(withId(R.id.view_pager)).perform(swipeLeft())

        onView(
            allOf(
                withResourceName("iv_mood_state"),
                withTagValue(equalTo(R.drawable.smiley_normal))
            )
        ).perform(click())
        onView(withText("Mood saved"))
            .inRoot(withDecorView(not(activityTestRule.activity.window.decorView)))
            .check(ViewAssertions.matches(isDisplayed()))


        onView(withId(R.id.view_pager)).perform(swipeLeft())

        onView(
            allOf(
                withResourceName("iv_mood_state"),
                withTagValue(equalTo(R.drawable.smiley_disappointed))
            )
        ).perform(click())
        onView(withText("Mood saved"))
            .inRoot(withDecorView(not(activityTestRule.activity.window.decorView)))
            .check(ViewAssertions.matches(isDisplayed()))


        onView(withId(R.id.view_pager)).perform(swipeLeft())

        onView(
            allOf(
                withResourceName("iv_mood_state"),
                withTagValue(equalTo(R.drawable.smiley_sad))
            )
        ).perform(click())
        onView(withText("Mood saved"))
            .inRoot(withDecorView(not(activityTestRule.activity.window.decorView)))
            .check(ViewAssertions.matches(isDisplayed()))

    }

    @Test
    fun clicking_comment_button_shows_dialog() {
        activityTestRule.launchActivity(null)

        onView(
            allOf(
                withResourceName("iv_add_comment"),
                withTagValue(equalTo(R.drawable.smiley_super_happy))
            )
        ).perform(click())

        onView(withText("Add a comment")).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun clicking_history_button_launches_new_activity() {
        activityTestRule.launchActivity(null)

        onView(
            allOf(
                withResourceName("iv_go_to_history"),
                withTagValue(equalTo(R.drawable.smiley_super_happy))
            )
        ).perform(click())

        Intents.intended(hasComponent(HistoryActivity::class.java.name))
    }
}