package com.aimicor.httpnetwork.data

import com.aimicor.httpnetwork.domain.HttpError
import com.aimicor.httpnetwork.domain.HttpResult
import io.ktor.client.network.sockets.SocketTimeoutException
import kotlinx.coroutines.yield

suspend fun <T> safeCall(call: suspend () -> T): HttpResult<T> {
    return try {
        val result = call()
        HttpResult.Success(result)
    } catch (throwable: Throwable) {
        if (isOffline(throwable)) {
            HttpResult.Failure(HttpError.Offline(throwable))
        } else {
            when (throwable) {
                is SocketTimeoutException -> HttpResult.Failure(HttpError.Timeout(throwable))
                else -> HttpResult.Failure(HttpError.Unknown(throwable))
            }
        }
    }
}

suspend fun <T> withRetries(retries: Int, call: suspend () -> T): T {
    return try {
        yield()
        call()
    } catch (throwable: Throwable) {
        if (retries > 0) {
            withRetries(retries - 1, call)
        } else {
            throw throwable
        }
    }
}
