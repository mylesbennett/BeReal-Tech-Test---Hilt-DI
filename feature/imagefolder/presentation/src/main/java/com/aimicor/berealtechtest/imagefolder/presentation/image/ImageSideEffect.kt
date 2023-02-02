package com.aimicor.berealtechtest.imagefolder.presentation.image

import com.aimicor.udfmvi.presentation.SideEffect

internal sealed class ImageSideEffect : SideEffect {
    object Close : ImageSideEffect()
}
