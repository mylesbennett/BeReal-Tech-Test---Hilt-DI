package com.aimicor.berealtechtest.imagefolder.presentation.image

import com.aimicor.udfmvi.presentation.Event

internal sealed class ImageEvent: Event {
    object OnCloseClicked : ImageEvent()
}
