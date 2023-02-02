package com.aimicor.berealtechtest.imagefolder.presentation

import com.aimicor.udfmvi.presentation.SideEffect

internal sealed class NavSideEffect: SideEffect{
    object Close : NavSideEffect()
    data class GoToFolder(val id: String): NavSideEffect()
    data class GoToImage(val id: String): NavSideEffect()
}
