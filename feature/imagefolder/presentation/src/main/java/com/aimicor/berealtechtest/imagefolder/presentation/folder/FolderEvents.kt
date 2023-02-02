package com.aimicor.berealtechtest.imagefolder.presentation.folder

import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderDetails
import com.aimicor.udfmvi.presentation.Event

internal sealed class FolderEvents: Event {
    object OnCloseClicked : FolderEvents()
    data class OnItemClicked(val item: ImageFolderDetails): FolderEvents()
}
