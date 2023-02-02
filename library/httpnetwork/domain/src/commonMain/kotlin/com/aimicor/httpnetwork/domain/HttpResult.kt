package com.aimicor.httpnetwork.domain

sealed class HttpResult<out T> {
    data class Success<out T>(val data: T) : HttpResult<T>()
    data class Failure<T>(val error: HttpError) : HttpResult<T>()
}
