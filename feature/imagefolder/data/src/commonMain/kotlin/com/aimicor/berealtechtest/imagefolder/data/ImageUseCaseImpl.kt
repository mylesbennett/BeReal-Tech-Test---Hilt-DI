package com.aimicor.berealtechtest.imagefolder.data

import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderRepository
import com.aimicor.berealtechtest.imagefolder.domain.ImageUseCase
import com.aimicor.httpnetwork.domain.HttpResult

class ImageUseCaseImpl(
    private val repository: ImageFolderRepository = ImageFolderRepositoryImpl()
):ImageUseCase {

    override suspend fun invoke(id: String): HttpResult<ByteArray> = repository.getImage(id)
}
