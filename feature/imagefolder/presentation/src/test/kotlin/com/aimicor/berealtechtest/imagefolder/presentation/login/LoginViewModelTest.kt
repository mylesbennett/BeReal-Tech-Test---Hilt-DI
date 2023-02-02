package com.aimicor.berealtechtest.imagefolder.presentation.login

import app.cash.turbine.test
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderDetails
import com.aimicor.berealtechtest.imagefolder.domain.ImageFolderLoginUseCase
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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    var coroutinesRule = TestCoroutineRule()

    private val loginUseCase: ImageFolderLoginUseCase = mockk()

    private val viewModel by lazy {
        LoginViewModel(loginUseCase)
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
    fun `given login when folder fetched successfully then state is success`() = runTest {
        // Given
        val testId = "some_id"
        val username = "username"
        val password = "password"
        val results = mockk<ImageFolderDetails> {
            every { id } returns testId
        }
        coEvery { loginUseCase(username, password) } returns HttpResult.Success(results)

        // When
        viewModel.handleEvent(LoginEvent.Login(username, password))

        // Then
        viewModel.sideEffect.test {
            awaitItem().apply {
                assertTrue(this is NavSideEffect.GoToFolder)
                assertEquals(testId, (this as NavSideEffect.GoToFolder).id)
            }
        }
    }

    @Test
    fun `given vm start when folder fetch fails then state is fail`() = runTest {
        // Given
        coEvery { loginUseCase(any(), any()) } returns HttpResult.Failure(
            HttpError.Unknown(
                Throwable()
            )
        )

        // When
        viewModel.handleEvent(LoginEvent.Login("", ""))

        // Then
        viewModel.uiState.test {
            awaitItem().apply {
                assertEquals(FetchStatus.FAIL, fetchStatus)
            }
        }
    }

}
