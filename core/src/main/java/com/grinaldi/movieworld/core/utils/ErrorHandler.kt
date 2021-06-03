package com.grinaldi.movieworld.core.utils

import retrofit2.HttpException
import java.net.UnknownHostException

object ErrorHandler {
    fun getErrorMessage(error: Throwable): String {
        return when (error) {
            is UnknownHostException -> "Network Error"
            is HttpException -> "HTTP Error"
            else -> error.message.toString()
        }
    }
}