package com.aimicor.berealtechtest.imagefolder.presentation.image

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.aimicor.berealtechtest.imagefolder.domain.ImageUseCase
import com.aimicor.berealtechtest.imagefolder.presentation.FetchStatus
import com.aimicor.composenav.presentation.getParam
import com.aimicor.httpnetwork.domain.HttpResult
import com.aimicor.udfmvi.presentation.UdfViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

internal const val IMAGE_ID = "FolderId"

@HiltViewModel
internal class ImageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val imageUseCase: ImageUseCase
) : UdfViewModel<ImageEvent, ImageUiState, ImageSideEffect>() {

    init {
        savedStateHandle.getParam<String>(IMAGE_ID)?.let { id ->
            viewModelScope.launch { imageUseCase(id).process() }
        }
    }

    override fun handleEvent(event: ImageEvent) {
        (event as? ImageEvent.OnCloseClicked)?.let {
            sendSideEffect { ImageSideEffect.Close }
        }
    }

    override fun startingUiState() = ImageUiState(
        fetchStatus = FetchStatus.LOADING,
        imageData = ByteArray(0)
    )

    private fun HttpResult<ByteArray>.process() {
        when(this){
            is HttpResult.Failure -> setUiState {
                copy(
                    fetchStatus = FetchStatus.FAIL
                )
            }
            is HttpResult.Success -> setUiState {
                copy(
                    fetchStatus = FetchStatus.SUCCESS,
                    imageData = data
                )
            }
        }
    }
}
