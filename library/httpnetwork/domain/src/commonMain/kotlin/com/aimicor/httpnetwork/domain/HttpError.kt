package com.aimicor.httpnetwork.domain

sealed class HttpError(val throwable: Throwable) {
    class Offline(throwable: Throwable) : HttpError(throwable)
    class Timeout(throwable: Throwable) : HttpError(throwable)
    class Unknown(throwable: Throwable) : HttpError(throwable)
}
