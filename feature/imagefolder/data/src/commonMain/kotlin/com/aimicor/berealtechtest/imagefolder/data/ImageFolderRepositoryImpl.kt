package com.aimicor.berealtechtest.imagefolder.data

import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderDetails
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderRepository
import com.aimicor.httpnetwork.data.safeCall
import com.aimicor.httpnetwork.domain.HttpResult

val imageFolderRepository: ImageFolderRepository = ImageFolderRepositoryImpl()

internal class ImageFolderRepositoryImpl(
    private val service: ImageFolderService = ImageFolderService()
) : ImageFolderRepository {

    private val ImageFolderResponse.toDomain: ImageFolderDetails
        get() = let { response ->
            ImageFolderDetails(
                id = response.id.orEmpty(),
                parentId = response.parentId.orEmpty(),
                name = response.name.orEmpty(),
                isDir = response.isDir ?: true,
                size = response.size,
                contentType = response.contentType,
                modificationDate = response.modificationDate.orEmpty()
            )
        }

    override suspend fun loginAndGetFolder(
        username: String,
        password: String
    ): HttpResult<ImageFolderDetails> = safeCall {
        service
            .loginAndGetFolder(username, password)
            .rootItem
            ?.toDomain
            ?: throw Exception()
    }


    override suspend fun getFolder(
        id: String
    ): HttpResult<List<ImageFolderDetails>> = safeCall {
        service
            .getFolder(id)
            .run { map { it.toDomain } }
            .sortedWith(compareBy({ !it.isDir }, { it.name }))
    }

    override suspend fun getImage(
        id: String
    ): HttpResult<ByteArray> = safeCall { service.getImage(id) }
}
