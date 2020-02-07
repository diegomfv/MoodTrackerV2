package com.diegomfv.moodtrackerv2.utils

import com.diegomfv.moodtrackerv2.constants.listOfDaysAsString
import com.diegomfv.moodtrackerv2.domain.DayModel
import org.junit.Test
import kotlin.random.Random

class GeneralTest {

    @Test
    fun fold_reduce() {

        (listOfDaysAsString.indices)
            .toList()
            .fold (mutableListOf<DayModel>()) { acc, n ->
                acc.apply { add(DayModel.emptyDay(n).copy(dayAsString = listOfDaysAsString[n], mood = Random.nextInt(0, 5), comment = "")) }
            }
            .forEach { println(it) }

    }
}