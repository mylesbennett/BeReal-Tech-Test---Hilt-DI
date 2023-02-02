package com.aimicor.berealtechtest.imagefolder.presentation.image

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderDetails
import com.aimicor.berealtechtest.imagefolder.domain.ImageUseCase
import com.aimicor.berealtechtest.imagefolder.presentation.FetchStatus
import com.aimicor.berealtechtest.imagefolder.presentation.folder.FOLDER_ID
import com.aimicor.httpnetwork.domain.HttpError
import com.aimicor.httpnetwork.domain.HttpResult
import com.aimicor.test.TestCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class ImageViewModelTest {

    @get:Rule
    var coroutinesRule = TestCoroutineRule()

    private val imageUseCase: ImageUseCase = mockk()
    private val savedHandleState: SavedStateHandle = SavedStateHandle()

    private val viewModel by lazy {
        ImageViewModel(savedHandleState, imageUseCase)
    }

    @Test
    fun `given back button event then effect is sent to view`() = runTest {
        // Given
        viewModel.handleEvent(ImageEvent.OnCloseClicked)

        // Then
        viewModel.sideEffect.test {
            assertTrue(awaitItem() is ImageSideEffect.Close)
        }
    }

    @Test
    fun `given vm start then state is loading`() = runTest {
        viewModel.uiState.test {
            awaitItem().apply {
                Assert.assertEquals(FetchStatus.LOADING, fetchStatus)
            }
        }
    }

    @Test
    fun `given vm start when folder fetched successfully then state is success`() = runTest {
        // Given
        val id = "some_id"
        val results = ByteArray(1)
        savedHandleState[FOLDER_ID] = "\"$id\""
        coEvery { imageUseCase(id) } returns HttpResult.Success(results)

        // When
        viewModel.uiState.test {
            awaitItem().apply {

                // Then
                Assert.assertEquals(FetchStatus.SUCCESS, fetchStatus)
                Assert.assertEquals(results, imageData)
            }
        }
    }

    @Test
    fun `given vm start when folder fetch fails then state is fail`() = runTest {
        // Given
        val id = "some_id"
        savedHandleState[FOLDER_ID] = "\"$id\""
        coEvery { imageUseCase(id) } returns HttpResult.Failure(HttpError.Unknown(Throwable()))

        // When
        viewModel.uiState.test {
            awaitItem().apply {

                // Then
                Assert.assertEquals(FetchStatus.FAIL, fetchStatus)
            }
        }
    }
}
