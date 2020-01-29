package com.diegomfv.moodtrackerv2.data


data class MoodStateModel(
    val moodState: Int,
    val moodComment: String
)

/* moodState
* 0: sad
* 1: disappointed
* 2: normal
* 3: happy
* 4: vey happy
* */