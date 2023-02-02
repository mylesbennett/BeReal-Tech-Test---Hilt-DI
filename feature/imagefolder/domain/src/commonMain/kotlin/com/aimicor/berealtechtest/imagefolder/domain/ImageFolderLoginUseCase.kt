package com.aimicor.berealtechtest.imagefolder.domain

import com.aimicor.httpnetwork.domain.HttpResult

interface ImageFolderLoginUseCase {

    suspend operator fun invoke(
        userName: String,
        password: String
    ): HttpResult<ImageFolderDetails>
}
