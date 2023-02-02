package com.aimicor.berealtechtest.imagefolder.presentation.image

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aimicor.composenav.presentation.goBack
import com.aimicor.udfmvi.presentation.rememberCollectWithLifecycle

internal const val IMAGE_SCREEN = "ImageScreen"

@Composable
internal fun ImageScreen(
    navController: NavController,
    viewModel: ImageViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.rememberCollectWithLifecycle()
    ImageContent(state, viewModel::handleEvent)

    BackHandler { viewModel.handleEvent(ImageEvent.OnCloseClicked) }

    LaunchedEffect(true) {
        viewModel.sideEffect.collect { navController.handleSideEffect() }
    }
}

private fun NavController.handleSideEffect() = goBack(context)
