package com.aimicor.berealtechtest.imagefolder.presentation.login

import androidx.compose.runtime.*
import com.aimicor.berealtechtest.imagefolder.presentation.ErrorContent
import com.aimicor.berealtechtest.imagefolder.presentation.FetchStatus
import com.aimicor.berealtechtest.imagefolder.presentation.LoadingContent

@Composable
internal fun LoginContent(
    state: LoginUiState,
    event: (LoginEvent) -> Unit
) {
    when (state.fetchStatus) {
        FetchStatus.LOADING -> LoadingContent()
        FetchStatus.FAIL -> ErrorContent()
        FetchStatus.SUCCESS -> {}
    }

    // TODO replace with dialog for credentials
    event(LoginEvent.Login("noel", "foobar"))
}

