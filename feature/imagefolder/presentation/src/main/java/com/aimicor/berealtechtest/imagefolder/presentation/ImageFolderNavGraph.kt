package com.aimicor.berealtechtest.imagefolder.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.aimicor.berealtechtest.imagefolder.presentation.folder.FOLDER_ID
import com.aimicor.berealtechtest.imagefolder.presentation.folder.FOLDER_SCREEN
import com.aimicor.berealtechtest.imagefolder.presentation.folder.FolderScreen
import com.aimicor.berealtechtest.imagefolder.presentation.folder.IS_SUBFOLDER
import com.aimicor.berealtechtest.imagefolder.presentation.image.IMAGE_ID
import com.aimicor.berealtechtest.imagefolder.presentation.image.IMAGE_SCREEN
import com.aimicor.berealtechtest.imagefolder.presentation.image.ImageScreen
import com.aimicor.berealtechtest.imagefolder.presentation.login.LOGIN_SCREEN
import com.aimicor.berealtechtest.imagefolder.presentation.login.LoginScreen
import com.aimicor.composenav.presentation.withParam

const val IMAGE_FOLDER_FEATURE = "ImageFolderFeature"

@ExperimentalAnimationApi
fun NavGraphBuilder.imageFolderGraph(
    navController: NavController
) {
    navigation(
        startDestination = LOGIN_SCREEN,
        route = IMAGE_FOLDER_FEATURE,
        enterTransition = { fadeIn(animationSpec = tween(2000)) }
    ) {
        composable(LOGIN_SCREEN) { LoginScreen(navController) }
        composable(IMAGE_SCREEN.withParam(IMAGE_ID)) { ImageScreen(navController) }
        composable(
            FOLDER_SCREEN
                .withParam(FOLDER_ID)
                .withParam(IS_SUBFOLDER)
        ) { FolderScreen(navController) }

        // ... more screens
    }
}
