package com.diegomfv.moodtrackerv2.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class DayModelTest {

    @Test
    fun constructorTest () {
        val model = DayModel(
            "itemId",
            "day",
            0,
            "comment")

        assertEquals(model.itemId, "itemId")
        assertEquals(model.day, "day")
        assertEquals(model.mood, 0)
        assertEquals(model.comment, "comment")
    }

}