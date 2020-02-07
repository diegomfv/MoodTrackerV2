package com.diegomfv.moodtrackerv2.utils

import android.os.Build
import com.diegomfv.moodtrackerv2.data.LocalDataSource
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

object DateTimeManager {

    fun getCurrentTimeInMillis () : Long {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Date().toInstant().toEpochMilli()
        } else {
            Date().time
        }
    }

//    fun getLocalDateTimeFromMillis (localDataSource: LocalDataSource, millis: Long) : LocalDateTime {
//        return Date(millis)
//
//
//        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val lastSessionMillis = localDataSource.getLastSessionMillis()
//            LocalDateTime.ofInstant(Instant.ofEpochMilli(lastSessionMillis), TimeZone.getDefault().toZoneId())
//
//        } else {
//            val cal = GregorianCalendar.getInstance()
//            cal.time = timestamp
//
//
//        }
//
//
//
//        val lastSessionLocalDateTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//        } else {
//            TODO("VERSION.SDK_INT < O")
//        }
//
//
//
//    }

}