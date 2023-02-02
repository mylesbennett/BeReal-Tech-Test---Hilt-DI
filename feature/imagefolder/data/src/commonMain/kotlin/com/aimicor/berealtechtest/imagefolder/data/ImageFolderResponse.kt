package com.aimicor.berealtechtest.imagefolder.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageFolderResponse(
    @SerialName("id") val id: String? = null,
    @SerialName("parentId") val parentId: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("isDir") val isDir: Boolean? = null,
    @SerialName("size") val size: Int? = null,
    @SerialName("contentType") val contentType: String? = null,
    @SerialName("modificationDate") val modificationDate: String? = null
)
