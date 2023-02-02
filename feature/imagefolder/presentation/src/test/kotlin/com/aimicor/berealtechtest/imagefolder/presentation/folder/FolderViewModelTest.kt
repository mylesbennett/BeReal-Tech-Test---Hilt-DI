package com.aimicor.berealtechtest.imagefolder.presentation.folder

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderDetails
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderUseCase
import com.aimicor.berealtechtest.imagefolder.presentation.FetchStatus
import com.aimicor.berealtechtest.imagefolder.presentation.NavSideEffect
import com.aimicor.httpnetwork.domain.HttpError
import com.aimicor.httpnetwork.domain.HttpResult
import com.aimicor.test.TestCoroutineRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class FolderViewModelTest {

    @get:Rule
    var coroutinesRule = TestCoroutineRule()

    private val folderUseCase: ImageFolderUseCase = mockk()
    private val savedHandleState: SavedStateHandle = SavedStateHandle()

    private val viewModel by lazy {
        FolderViewModel(savedHandleState, folderUseCase)
    }

    @Test
    fun `given back button event then effect is sent to view`() = runTest {
        // Given
        viewModel.handleEvent(FolderEvents.OnCloseClicked)

        // Then
        viewModel.sideEffect.test {
            assertTrue(awaitItem() is NavSideEffect.Close)
        }
    }

    @Test
    fun `given folder click event then effect is sent to view`() = runTest {
        // Given
        val testId = "some_id"
        val item = mockk<ImageFolderDetails> {
            every { id } returns testId
            every { isDir } returns true
        }
        viewModel.handleEvent(FolderEvents.OnItemClicked(item))

        // Then
        viewModel.sideEffect.test {
            awaitItem().apply {
                assertTrue(this is NavSideEffect.GoToFolder)
                assertEquals(testId, (this as NavSideEffect.GoToFolder).id)
            }
        }
    }

    @Test
    fun `given image click event then effect is sent to view`() = runTest {
        // Given
        val testId = "some_id"
        val item = mockk<ImageFolderDetails> {
            every { id } returns testId
            every { isDir } returns false
        }
        viewModel.handleEvent(FolderEvents.OnItemClicked(item))

        // Then
        viewModel.sideEffect.test {
            awaitItem().apply {
                assertTrue(this is NavSideEffect.GoToImage)
                assertEquals(testId, (this as NavSideEffect.GoToImage).id)
            }
        }
    }

    @Test
    fun `given vm start then state is loading`() = runTest {
        viewModel.uiState.test {
            awaitItem().apply {
                assertEquals(FetchStatus.LOADING, fetchStatus)
            }
        }
    }

    @Test
    fun `given vm start with subfolder when folder fetched successfully then state is success and subfolder flag not set`() =
        runTest {
            // Given
            val id = "some_id"
            val results = listOf<ImageFolderDetails>(mockk())
            savedHandleState[FOLDER_ID] = "\"$id\""
            savedHandleState[IS_SUBFOLDER] = "true"
            coEvery { folderUseCase(id) } returns HttpResult.Success(results)

            // When
            viewModel.uiState.test {
                awaitItem().apply {

                    // Then
                    assertEquals(FetchStatus.SUCCESS, fetchStatus)
                    assertEquals(results, folderItems)
                    assertTrue(isSubfolder)
                }
            }
        }

    @Test
    fun `given vm start with root when folder fetched successfully then state is success and subfolder flag set`() =
        runTest {
            // Given
            val id = "some_id"
            val results = listOf<ImageFolderDetails>(mockk())
            savedHandleState[FOLDER_ID] = "\"$id\""
            savedHandleState[IS_SUBFOLDER] = "false"
            coEvery { folderUseCase(id) } returns HttpResult.Success(results)

            // When
            viewModel.uiState.test {
                awaitItem().apply {

                    // Then
                    assertEquals(FetchStatus.SUCCESS, fetchStatus)
                    assertEquals(results, folderItems)
                    assertFalse(isSubfolder)
                }
            }
        }

    @Test
    fun `given vm start when folder fetch fails then state is fail`() = runTest {
        // Given
        val id = "some_id"
        savedHandleState[FOLDER_ID] = "\"$id\""
        coEvery { folderUseCase(id) } returns HttpResult.Failure(HttpError.Unknown(Throwable()))

        // When
        viewModel.uiState.test {
            awaitItem().apply {

                // Then
                assertEquals(FetchStatus.FAIL, fetchStatus)
            }
        }
    }
}
