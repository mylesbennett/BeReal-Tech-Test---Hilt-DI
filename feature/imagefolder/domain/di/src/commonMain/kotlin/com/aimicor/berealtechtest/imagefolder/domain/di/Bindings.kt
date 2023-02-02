package com.aimicor.berealtechtest.imagefolder.domain.di

import com.aimicor.berealtechtest.imagefolder.data.ImageFolderLoginUseCaseImpl
import com.aimicor.berealtechtest.imagefolder.data.ImageFolderUseCaseImpl
import com.aimicor.berealtechtest.imagefolder.data.ImageUseCaseImpl
import com.aimicor.berealtechtest.imagefolder.data.imageFolderRepository
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderLoginUseCase
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderUseCase
import com.aimicor.berealtechtest.imagefolder.domain.ImageUseCase

object Bindings {

    val imageFolderLoginUseCase: ImageFolderLoginUseCase =
        ImageFolderLoginUseCaseImpl(imageFolderRepository)

    val imageFolderUseCase: ImageFolderUseCase =
        ImageFolderUseCaseImpl(imageFolderRepository)

    val imageUseCase: ImageUseCase =
        ImageUseCaseImpl(imageFolderRepository)

}
