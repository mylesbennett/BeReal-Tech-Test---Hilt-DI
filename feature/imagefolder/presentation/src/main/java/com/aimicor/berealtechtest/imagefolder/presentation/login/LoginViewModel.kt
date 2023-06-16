package com.aimicor.berealtechtest.imagefolder.presentation.login

import androidx.lifecycle.viewModelScope
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderDetails
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderLoginUseCase
import com.aimicor.berealtechtest.imagefolder.presentation.FetchStatus
import com.aimicor.berealtechtest.imagefolder.presentation.NavSideEffect
import com.aimicor.httpnetwork.domain.HttpResult
import com.aimicor.udfmvi.presentation.UdfViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val loginUseCase: ImageFolderLoginUseCase
) : UdfViewModel<LoginEvent, LoginUiState, NavSideEffect>(
    initialUiState = LoginUiState(FetchStatus.LOADING)
) {

    override fun handleEvent(event: LoginEvent) {
        (event as? LoginEvent.Login)?.apply {
            viewModelScope.launch {
                loginUseCase(event.userName, event.password).process()
            }
        }
    }

    private fun HttpResult<ImageFolderDetails>.process() {
        when (this) {
            is HttpResult.Failure -> setUiState { copy(fetchStatus = FetchStatus.FAIL) }
            is HttpResult.Success -> sendSideEffect { NavSideEffect.GoToFolder(data.id) }
        }
    }
}
