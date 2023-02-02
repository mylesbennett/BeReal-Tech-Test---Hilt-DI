package com.aimicor.berealtechtest.imagefolder.domain.di

import com.aimicor.berealtechtest.imagefolder.data.ImageFolderLoginUseCaseImpl
import com.aimicor.berealtechtest.imagefolder.data.ImageFolderUseCaseImpl
import com.aimicor.berealtechtest.imagefolder.data.ImageUseCaseImpl
import com.aimicor.berealtechtest.imagefolder.data.imageFolderRepository
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderLoginUseCase
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderUseCase
import com.aimicor.berealtechtest.imagefolder.domain.ImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideImageFolderLoginUseCase(): ImageFolderLoginUseCase =
        ImageFolderLoginUseCaseImpl(imageFolderRepository)

    @Provides
    fun provideImageFolderUseCase(): ImageFolderUseCase =
        ImageFolderUseCaseImpl(imageFolderRepository)

    @Provides
    fun provideImageUseCase(): ImageUseCase =
        ImageUseCaseImpl(imageFolderRepository)
}
