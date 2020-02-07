package com.diegomfv.moodtrackerv2

import com.diegomfv.moodtrackerv2.data.FakeLocalDataSource
import com.diegomfv.moodtrackerv2.utils.DaysAheadDetector
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DaysAheadDetectorTest {

    val mockedLocalDataSource = FakeLocalDataSource()
    lateinit var daysAheadDetector: DaysAheadDetector

    @Before
    fun setUp () {
        daysAheadDetector = DaysAheadDetector(mockedLocalDataSource)
    }

    @Test
    fun after () {

    }


    @Test
    fun resolveDaysAheadTest() {

        val daysAheadDetector = DaysAheadDetector(mock())

//        whenever(daysAheadDetector.getCurrentSession()).doReturn()
//        whenever(daysAheadDetector.getLastSessionMidgnight()).dorE


    }

}