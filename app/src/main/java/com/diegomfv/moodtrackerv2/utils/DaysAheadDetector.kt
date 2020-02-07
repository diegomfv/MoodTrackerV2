package com.diegomfv.moodtrackerv2.utils

import com.diegomfv.moodtrackerv2.data.LocalDataSource
import java.time.*
import java.util.*


class DaysAheadDetector(
    private val localDataSource: LocalDataSource
) {

    private val oneDay = 1000 * 60 * 60 * 24

    suspend fun resolveDaysAhead(): Int {
        val lastSessionMidnight = getLastSessionMidgnight()
        val currentSession = getCurrentSession ()

        val lastMidnightMillis = lastSessionMidnight.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()
        val currentMillis = currentSession.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()

        val diff = currentMillis - lastMidnightMillis
        if (diff < 0) return 0

        val diffInDays = (diff.toFloat() / oneDay)

        return if (diffInDays > 0) {
            (diffInDays + 1).toInt()

        } else {
            0
        }
    }

    suspend fun getLastSessionMidgnight(): LocalDateTime {
        val lastSessionLocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(localDataSource.getLastSessionMillis()), TimeZone.getDefault().toZoneId())
        return LocalDateTime.of(lastSessionLocalDateTime.toLocalDate(), LocalTime.MIDNIGHT).plusDays(1)
    }

    fun getCurrentSession() : LocalDateTime = LocalDateTime.now(TimeZone.getDefault().toZoneId())
}