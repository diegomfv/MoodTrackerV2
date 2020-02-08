package com.diegomfv.moodtrackerv2.utils


/** This class decides the size of the dialogs
 * */
object DialogSizeManager {

    //TODO Extend
    fun getDialogHeight () : Int {
        return (Utils.getScreenHeight() * 0.5).toInt()
    }

    //TODO Extend
    fun getDialogWidth () : Int {
        return (Utils.getScreenWidth() * 0.7).toInt()
    }

}