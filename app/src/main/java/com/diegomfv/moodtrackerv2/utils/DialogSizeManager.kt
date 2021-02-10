package com.diegomfv.moodtrackerv2.utils

import android.content.res.Configuration
import android.view.Window


/**
 * This class decides the size of the dialogs
 * TODO This has to be extended for different devices
 * */
object DialogSizeManager {

    fun setDialogMeasure (window: Window?, orientation: Int?) {
        if (window == null) return
        if (orientation == null || orientation == -1) return
        window.setLayout(getDialogWidth(orientation), getDialogHeight(orientation))
    }

    private fun getDialogHeight (orientation: Int) : Int {
        return if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            (Utils.getScreenHeight() * 0.8).toInt()
        } else {
            (Utils.getScreenHeight() * 0.9).toInt()
        }
    }

    private fun getDialogWidth (orientation: Int) : Int {
        return if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            (Utils.getScreenWidth() * 0.8).toInt()
        } else {
            (Utils.getScreenWidth() * 0.7).toInt()
        }
    }

}