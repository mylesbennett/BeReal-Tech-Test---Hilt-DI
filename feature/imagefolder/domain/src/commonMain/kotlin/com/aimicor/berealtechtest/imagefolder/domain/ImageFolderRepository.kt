package com.aimicor.berealtechtest.imagefolder.domain

import com.aimicor.httpnetwork.domain.HttpResult

interface ImageFolderRepository {

    suspend fun loginAndGetFolder(
        username: String,
        password: String
    ): HttpResult<ImageFolderDetails>

    suspend fun getFolder(
        id: String
    ): HttpResult<List<ImageFolderDetails>>

    suspend fun getImage(
        id: String
    ): HttpResult<ByteArray>
}
