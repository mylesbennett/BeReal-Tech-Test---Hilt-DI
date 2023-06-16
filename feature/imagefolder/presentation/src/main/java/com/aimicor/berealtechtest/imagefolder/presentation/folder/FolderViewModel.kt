package com.aimicor.berealtechtest.imagefolder.presentation.folder

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderDetails
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderUseCase
import com.aimicor.berealtechtest.imagefolder.presentation.FetchStatus
import com.aimicor.berealtechtest.imagefolder.presentation.NavSideEffect
import com.aimicor.composenav.presentation.getParam
import com.aimicor.httpnetwork.domain.HttpResult
import com.aimicor.udfmvi.presentation.UdfViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

internal const val FOLDER_ID = "FolderId"
internal const val IS_SUBFOLDER = "IsSubFolder"

@HiltViewModel
internal class FolderViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val folderUseCase: ImageFolderUseCase
) : UdfViewModel<FolderEvents, FolderUiState, NavSideEffect>(
    initialUiState = FolderUiState(
        fetchStatus = FetchStatus.LOADING,
        isSubfolder = true,
        folderItems = emptyList()
    )
) {

    init {
        savedStateHandle.getParam<String>(FOLDER_ID)?.let { id ->
            viewModelScope.launch { folderUseCase(id).process() }
        }
    }

    override fun handleEvent(event: FolderEvents) {
        when (event) {
            FolderEvents.OnCloseClicked -> sendSideEffect { NavSideEffect.Close }
            is FolderEvents.OnItemClicked -> event.navigateToView()
        }
    }

    private fun HttpResult<List<ImageFolderDetails>>.process() {
        when (this) {
            is HttpResult.Failure -> setUiState {
                copy(
                    fetchStatus = FetchStatus.FAIL
                )
            }
            is HttpResult.Success -> setUiState {
                copy(
                    fetchStatus = FetchStatus.SUCCESS,
                    isSubfolder = savedStateHandle.getParam<Boolean>(IS_SUBFOLDER) ?: true,
                    folderItems = data
                )
            }
        }
    }

    private fun FolderEvents.OnItemClicked.navigateToView() =
        if (item.isDir) sendSideEffect { NavSideEffect.GoToFolder(item.id) }
        else sendSideEffect { NavSideEffect.GoToImage(item.id) }
}
