package com.aimicor.berealtechtest.imagefolder.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aimicor.berealtechtest.imagefolder.presentation.folder.FOLDER_SCREEN
import com.aimicor.berealtechtest.imagefolder.presentation.NavSideEffect
import com.aimicor.composenav.presentation.putParam
import com.aimicor.udfmvi.presentation.rememberCollectWithLifecycle

internal const val LOGIN_SCREEN = "LoginScreen"

@Composable
internal fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.rememberCollectWithLifecycle()
    LoginContent(state, viewModel::handleEvent)

    LaunchedEffect(true) { viewModel.sideEffect.collect { navController.handleSideEffect(it) } }
}

private fun NavController.handleSideEffect(navigation: NavSideEffect) {
    (navigation as? NavSideEffect.GoToFolder)?.let {
        navigate(
            FOLDER_SCREEN
                .putParam(navigation.id)
                .putParam(false)
        ) { popUpTo(0) }
    }
}
