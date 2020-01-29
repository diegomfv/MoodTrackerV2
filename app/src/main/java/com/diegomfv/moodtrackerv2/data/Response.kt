package com.diegomfv.moodtrackerv2.data

sealed class Response<out T> {
    class Success<out T>(val result: T) : Response<T>()
    class Failure(val throwable: Throwable) : Response<Nothing>()
}