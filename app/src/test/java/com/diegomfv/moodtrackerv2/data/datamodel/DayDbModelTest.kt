package com.diegomfv.moodtrackerv2.data.datamodel

import org.junit.Assert.assertEquals
import org.junit.Test

class DayDbModelTest {

    @Test
    fun constructorTest() {
        val model = DayDbModel(
            "itemId",
            "day",
            0,
            "comment"
        )

        assertEquals(model.itemId, "itemId")
        assertEquals(model.day, "day")
        assertEquals(model.mood, 0)
        assertEquals(model.comment, "comment")

    }

    @Test
    fun toDayModelTest() {
        val model = DayDbModel(
            "itemId",
            "day",
            0,
            "comment"
        )

        val result = model.toDayModel()
        assertEquals(result.itemId, "itemId")
        assertEquals(result.day, "day")
        assertEquals(result.mood, 0)
        assertEquals(result.comment, "comment")
    }


}