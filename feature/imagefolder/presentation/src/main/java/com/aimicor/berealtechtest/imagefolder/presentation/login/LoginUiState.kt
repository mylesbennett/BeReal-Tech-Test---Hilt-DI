package com.aimicor.berealtechtest.imagefolder.presentation.login

import com.aimicor.berealtechtest.imagefolder.presentation.FetchStatus
import com.aimicor.udfmvi.UiState

internal data class LoginUiState(val fetchStatus: FetchStatus): UiState
