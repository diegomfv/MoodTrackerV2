package com.diegomfv.moodtrackerv2.usecase

import com.diegomfv.moodtrackerv2.data.repository.Repository

class SaveCommentUsecase (
    private val repository: Repository
){

    suspend fun invoke (newComment: String) {
        repository.updateDay(null, newComment)
    }
}