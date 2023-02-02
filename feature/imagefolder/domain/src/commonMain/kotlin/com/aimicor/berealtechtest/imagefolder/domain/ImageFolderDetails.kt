package com.aimicor.berealtechtest.imagefolder.domain

import kotlinx.serialization.Serializable

@Serializable
data class ImageFolderDetails(
    val id: String,
    val parentId: String,
    val name: String,
    val isDir: Boolean,
    val size: Int? = null,
    val contentType: String? = null,
    val modificationDate: String
)
