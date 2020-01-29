package com.diegomfv.moodtrackerv2.usecase

import com.diegomfv.moodtrackerv2.data.Response
import com.diegomfv.moodtrackerv2.domain.DayModel
import kotlin.random.Random

class GetDaysUsecase {

    //TODO
    fun invoke () : Response<List<DayModel>> {
        return Response.Success(dummyData())
    }

    //TODO Remove
    fun dummyData () : List<DayModel> {
        var counter = 0

        fun addNewDay (dayAsString: String, comment: String? = null) : DayModel {
            return DayModel(counter++, dayAsString, Random.nextInt(0, 4), comment)
        }

        return listOf(
            addNewDay("Today"),
            addNewDay("Yesterday"),
            addNewDay("2 days ago", "Comment"),
            addNewDay("3 days ago"),
            addNewDay("4 days ago"),
            addNewDay("5 days ago", "Another comment"),
            addNewDay("6 days ago"),
            addNewDay("7 days ago")
        )
    }
}