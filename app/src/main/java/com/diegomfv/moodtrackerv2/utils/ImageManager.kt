package com.diegomfv.moodtrackerv2.utils

import com.diegomfv.moodtrackerv2.R

interface ImageManager {
    fun getFaceImage() : Int
}

class BasicImageManager (private val moodState: Int): ImageManager {
    override fun getFaceImage(): Int = when (moodState) {
        0 -> R.drawable.smiley_sad
        1 -> R.drawable.smiley_disappointed
        2 -> R.drawable.smiley_normal
        3 -> R.drawable.smiley_happy
        4 -> R.drawable.smiley_super_happy
        else -> R.drawable.smiley_normal
    }
}