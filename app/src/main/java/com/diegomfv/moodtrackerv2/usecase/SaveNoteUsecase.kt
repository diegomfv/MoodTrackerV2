package com.diegomfv.moodtrackerv2.usecase

import com.diegomfv.moodtrackerv2.data.LocalDataSource

class SaveNoteUsecase (
    private val sharedPrefDataSource: LocalDataSource
){

    fun invoke (note: String) {
        TODO()
    }
}