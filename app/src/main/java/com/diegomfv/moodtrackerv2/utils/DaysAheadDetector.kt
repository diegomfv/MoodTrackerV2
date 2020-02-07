package com.diegomfv.moodtrackerv2.utils

import com.diegomfv.moodtrackerv2.data.LocalDataSource
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*


class DaysAheadDetector (
    private val localDataSource: LocalDataSource
) {

    suspend fun resolveDaysAhead(): Int {
        return 2 //TODO
    }
}

//        val lastSessionMillis = localDataSource.getLastSessionMillis()
//        val lastSessionLocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(lastSessionMillis), TimeZone.getDefault().toZoneId())
//
//        val test_timestamp = 1499070300000L
//        val triggerTime = LocalDateTime.ofInstant(
//            Instant.ofEpochMilli(test_timestamp),
//            TimeZone.getDefault().toZoneId()
//        )
//
//
//        val lastSession = LocalDateTime.of(2020, Month.FEBRUARY, 5, 12, 30)
//        val lastSessionMidnight = LocalDateTime.of(lastSession.toLocalDate(), LocalTime.MIDNIGHT).plusDays(1)
//
//        val currentSession: LocalDateTime = LocalDateTime.of(2020, Month.FEBRUARY, 6, 1, 30)
//
//        if (currentSession.isBefore(lastSessionMidnight)) {
//            //do nothing
//
//        } else {
//            //push. how much?
//
//            //get amount of days ahead
//            val lastMid = lastSessionMidnight.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()
//            val current = currentSession.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()
//
//            val diff = current - lastMid
//            val diffInDays = (diff.toFloat() / oneDay)
//
//            if (diffInDays > 0) {
//                println("push ${(diffInDays + 1).toInt()}")
//
//            } else {
//                println("do not push")
//            }
//
//        }
//
//        return 0
//    }
//
//    fun String.toLocalDateTime () {
//        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
//        val dateTime = LocalDateTime.parse(this, formatter)
//    }
//
//    fun LocalDateTime.toFormattedString () {
//
//    }
//}