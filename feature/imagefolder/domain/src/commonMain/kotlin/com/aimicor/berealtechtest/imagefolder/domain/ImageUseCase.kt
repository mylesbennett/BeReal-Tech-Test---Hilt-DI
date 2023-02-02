package com.aimicor.berealtechtest.imagefolder.domain

import com.aimicor.httpnetwork.domain.HttpResult

interface ImageUseCase {

    suspend operator fun invoke(id: String): HttpResult<ByteArray>
}
