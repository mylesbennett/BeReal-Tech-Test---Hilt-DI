package com.aimicor.berealtechtest.imagefolder.domain

import com.aimicor.httpnetwork.domain.HttpResult

interface ImageFolderUseCase {

    suspend operator fun invoke(id: String): HttpResult<List<ImageFolderDetails>>
}
