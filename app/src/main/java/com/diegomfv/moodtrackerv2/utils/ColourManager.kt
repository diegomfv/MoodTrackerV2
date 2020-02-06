package com.diegomfv.moodtrackerv2.utils

import android.content.Context
import com.diegomfv.moodtrackerv2.R
import com.diegomfv.moodtrackerv2.constants.*

interface ColourManager {
    fun getMoodColour(): Int
    fun getMoodColour(moodState: Int?): Int
}

class BasicColourManager (private val context: Context, private val moodState: Int? = null): ColourManager {

    override fun getMoodColour(moodState: Int?): Int =
        when (moodState) {
            MOOD_SAD -> context.resources.getColor(R.color.sad_red, null)
            MOOD_DISAPPOINTED -> context.resources.getColor(R.color.disappointed_grey, null)
            MOOD_NORMAL -> context.resources.getColor(R.color.normal_blue, null)
            MOOD_HAPPY -> context.resources.getColor(R.color.happy_green, null)
            MOOD_VERY_HAPPY -> context.resources.getColor(R.color.very_happy_yellow, null)
            else -> context.resources.getColor(R.color.normal_blue, null)
        }

    override fun getMoodColour(): Int = getMoodColour(moodState)
}