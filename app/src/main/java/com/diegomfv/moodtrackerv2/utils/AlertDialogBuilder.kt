package com.diegomfv.moodtrackerv2.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.diegomfv.moodtrackerv2.R

object AlertDialogBuilder {

    fun showCommentAlertDialog(
        context: Context,
        actionOnPositiveButtonClicked: () -> Unit,
        actionOnNegativeButtonClicked: () -> Unit
    ) {

        val dialog = AlertDialog.Builder(context)
            .setView(LayoutInflater.from(context).inflate(R.layout.alert_dialog_comment, null))
            .create()

        dialog.apply {
            findViewById<View>(R.id.alertDialogBoxOK)?.setOnClickListener {
                actionOnPositiveButtonClicked.invoke()
            }
            findViewById<View>(R.id.alertDialogBoxCANCEL)?.setOnClickListener {
                actionOnNegativeButtonClicked.invoke()
            }
        }
        dialog.show()
    }
}