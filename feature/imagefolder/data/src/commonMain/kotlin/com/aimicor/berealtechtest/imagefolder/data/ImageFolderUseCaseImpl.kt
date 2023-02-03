package com.aimicor.berealtechtest.imagefolder.data

import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderDetails
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderRepository
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderUseCase
import com.aimicor.httpnetwork.domain.HttpResult

class ImageFolderUseCaseImpl(
    private val repository: ImageFolderRepository = ImageFolderRepositoryImpl()
): ImageFolderUseCase {

    override suspend fun invoke(
        id: String
    ): HttpResult<List<ImageFolderDetails>> = repository.getFolder(id)
}
