package com.diegomfv.moodtrackerv2.utils

import android.content.Context
import com.diegomfv.moodtrackerv2.R

interface ColourManager {
    fun getFaceColour(): Int
}

class BasicColourManager (private val context: Context, private val moodState: Int): ColourManager {
    override fun getFaceColour(): Int =
        when (moodState) {
            0 -> context.resources.getColor(R.color.sad_red, null)
            1 -> context.resources.getColor(R.color.disappointed_grey, null)
            2 -> context.resources.getColor(R.color.normal_blue, null)
            3 -> context.resources.getColor(R.color.happy_green, null)
            4 -> context.resources.getColor(R.color.very_happy_yellow, null)
            else -> context.resources.getColor(R.color.normal_blue, null)
        }
}