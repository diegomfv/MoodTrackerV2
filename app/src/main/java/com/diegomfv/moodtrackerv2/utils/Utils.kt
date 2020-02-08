package com.diegomfv.moodtrackerv2.utils

import android.content.res.Resources

object Utils {

    fun getScreenWidth() = Resources.getSystem().displayMetrics.widthPixels.toFloat()
    fun getScreenHeight() = Resources.getSystem().displayMetrics.heightPixels.toFloat()

}