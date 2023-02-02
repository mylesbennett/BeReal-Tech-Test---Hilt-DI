package com.aimicor.berealtechtest.imagefolder.presentation.folder

import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderDetails
import com.aimicor.berealtechtest.imagefolder.presentation.FetchStatus
import com.aimicor.udfmvi.UiState

internal data class FolderUiState(
    val fetchStatus: FetchStatus,
    val isSubfolder: Boolean,
    val folderItems: List<ImageFolderDetails>
): UiState
