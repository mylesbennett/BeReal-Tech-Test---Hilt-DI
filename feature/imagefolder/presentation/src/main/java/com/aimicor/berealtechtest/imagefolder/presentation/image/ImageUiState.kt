package com.aimicor.berealtechtest.imagefolder.presentation.image

import com.aimicor.berealtechtest.imagefolder.presentation.FetchStatus
import com.aimicor.udfmvi.UiState

internal data class ImageUiState(
    val fetchStatus: FetchStatus,
    val imageData: ByteArray
): UiState {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ImageUiState

        if (fetchStatus != other.fetchStatus) return false
        if (!imageData.contentEquals(other.imageData)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fetchStatus.hashCode()
        result = 31 * result + imageData.contentHashCode()
        return result
    }
}
