package com.diegomfv.moodtrackerv2

import android.annotation.SuppressLint
import android.os.Build
import org.junit.Test
import java.text.SimpleDateFormat
import java.time.*
import java.util.*
import kotlin.math.nextTowards
import kotlin.math.roundToInt


class DaysAheadDetectorTest {

    val localDateTimePattern = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    val fixerDatePattern = "yyyy-MM-dd"
    val dayPattern = "dd"
    val UIdatePattern = "dd/MM/yyyy"

    fun daysAhead () {

    }

    val oneDay = 1000 * 60 * 60 * 24

    @Test
    fun daysAheadDetectorTest () {

        val lastSession = LocalDateTime.of(2020, Month.FEBRUARY, 5, 12, 30)
        val lastSessionMidnight = LocalDateTime.of(lastSession.toLocalDate(), LocalTime.MIDNIGHT).plusDays(1)

        val currentSession: LocalDateTime = LocalDateTime.of(2020, Month.FEBRUARY, 6, 1, 30)

        Date() < Date()


        if (currentSession.isBefore(lastSessionMidnight)) {
            //do nothing

        } else {
            //push. how much?

            //get amount of days ahead
            val lastMid = lastSessionMidnight.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()
            val current = currentSession.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()

            val diff = current - lastMid
            val diffInDays = (diff.toFloat() / oneDay)

            if (diffInDays > 0) {
                println("push ${(diffInDays + 1).toInt()}")

            } else {
                println("do not push")
            }

        }


//        val now: LocalDate = LocalDate.now()
//        val todayMidnight: LocalDateTime = LocalDateTime.of(now, LocalTime.MIDNIGHT)
//        val tomorrowMidnight: LocalDateTime = todayMidnight.plusDays(1)
//
//
//
//        println(daysAhead(dateTime))
//
//
//        val currentDateTimeMillis = Date().toInstant().toEpochMilli()
//        val dateToMillis = "2020-02-06'T'18:40:00.000".formatStringToDate(localDateTimePattern).toInstant().toEpochMilli()
//
//        println(currentDateTimeMillis)
//        println(dateToMillis)
//
//
//
//        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(localDateTimePattern)
//        val formattedDateTime = todayMidnight.format(formatter) // "1986-04-08 12:30"
//        println(tomorrowMidnight)

    }

    fun Date.transformDateAsDateObjectToMillis(date: Date): Long {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date.toInstant().toEpochMilli()
        } else {
            date.date.toLong()
        }
    }

    fun transformDateAsStringToMillis(dateAsString: String): Long {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dateAsString.formatStringToDate(UIdatePattern).toInstant().toEpochMilli()
        } else {
            dateAsString.formatStringToDate(UIdatePattern).date.toLong()
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun String.formatStringToDate(pattern: String): Date {
        val df = SimpleDateFormat(pattern)
        return try {
            df.parse(this)
        } catch (e: Exception) {
            Date()
        }
    }

}