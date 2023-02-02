package com.aimicor.berealtechtest.imagefolder.presentation.folder

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aimicor.berealtechtest.imagefolder.presentation.NavSideEffect
import com.aimicor.berealtechtest.imagefolder.presentation.image.IMAGE_SCREEN
import com.aimicor.composenav.presentation.goBack
import com.aimicor.composenav.presentation.putParam
import com.aimicor.udfmvi.presentation.rememberCollectWithLifecycle

internal const val FOLDER_SCREEN = "FolderScreen"

@Composable
internal fun FolderScreen(
    navController: NavController,
    viewModel: FolderViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.rememberCollectWithLifecycle()
    FolderContent(state, viewModel::handleEvent)

    BackHandler { viewModel.handleEvent(FolderEvents.OnCloseClicked) }

    LaunchedEffect(true) {
        viewModel.sideEffect.collect { navController.handleSideEffect(it) }
    }
}

private fun NavController.handleSideEffect(sideEffect: NavSideEffect) {
    when(sideEffect) {
        NavSideEffect.Close -> goBack(context)
        is NavSideEffect.GoToImage -> navigate(IMAGE_SCREEN.putParam(sideEffect.id))
        is NavSideEffect.GoToFolder -> navigate(
            FOLDER_SCREEN
                .putParam(sideEffect.id)
                .putParam(true)
        )
    }
}
