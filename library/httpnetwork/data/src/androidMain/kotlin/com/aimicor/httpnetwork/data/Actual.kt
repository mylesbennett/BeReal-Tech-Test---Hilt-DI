package com.aimicor.httpnetwork.data

import java.net.UnknownHostException

actual fun isOffline(throwable: Throwable): Boolean = throwable is UnknownHostException
