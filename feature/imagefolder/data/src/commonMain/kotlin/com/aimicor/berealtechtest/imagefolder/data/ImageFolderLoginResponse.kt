package com.aimicor.berealtechtest.imagefolder.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageFolderLoginResponse(
    @SerialName("firstName") val firstName: String? = null,
    @SerialName("lastName") val lastName: String? = null,
    @SerialName("rootItem") val rootItem: ImageFolderResponse? = null
)
