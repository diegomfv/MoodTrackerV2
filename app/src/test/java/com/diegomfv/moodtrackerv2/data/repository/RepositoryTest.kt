package com.diegomfv.moodtrackerv2.data.repository

import com.diegomfv.moodtrackerv2.data.LocalDataSource
import io.mockk.MockKAnnotations
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RepositoryTest {

    @MockK
    lateinit var localDataSource: LocalDataSource

    @InjectMockKs
    lateinit var repository: Repository

    @Before
    fun setUp () {
        MockKAnnotations.init(this)
    }

    @Test
    fun `updateDay when mood and comment are null does not update` () {
        coJustRun { localDataSource.updateOrCreateDay(any(), any()) }
        val repository = Repository(localDataSource)

        runBlocking {
            repository.updateDay(null, null)
            coVerify (exactly = 0) { localDataSource.updateOrCreateDay(null, null) }
        }
    }

    @Test
    fun `updateDay when mood is not null and comment is null updates` () {
        coJustRun { localDataSource.updateOrCreateDay(0, null) }
        val repository = Repository(localDataSource)

        runBlocking {
            repository.updateDay(0, null)
            coVerify (exactly = 1) { localDataSource.updateOrCreateDay(0, null) }
        }
    }

    @Test
    fun `updateDay when mood is null and comment is not null updates` () {
        coJustRun { localDataSource.updateOrCreateDay(null, "comment") }
        val repository = Repository(localDataSource)

        runBlocking {
            repository.updateDay(null, "comment")
            coVerify (exactly = 1) { localDataSource.updateOrCreateDay(null, "comment") }
        }
    }

}