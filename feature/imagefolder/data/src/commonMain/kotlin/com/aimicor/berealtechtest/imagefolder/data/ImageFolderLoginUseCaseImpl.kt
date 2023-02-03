package com.aimicor.berealtechtest.imagefolder.data

import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderDetails
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderLoginUseCase
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderRepository
import com.aimicor.httpnetwork.domain.HttpResult

class ImageFolderLoginUseCaseImpl(
    private val repository: ImageFolderRepository = ImageFolderRepositoryImpl()
): ImageFolderLoginUseCase {

    override suspend fun invoke(
        userName: String,
        password: String
    ): HttpResult<ImageFolderDetails> = repository.loginAndGetFolder(userName, password)
}
