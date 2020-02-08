package com.diegomfv.moodtrackerv2.usecase

import com.diegomfv.moodtrackerv2.data.LocalDataSource

class SaveCommentUsecase (
    private val sharedPrefDataSource: LocalDataSource
){

    suspend fun invoke (newComment: String) {
        sharedPrefDataSource.updateDayComment(newComment)
    }
}