package com.gabrielbmoro.crazymath.presentation.screens.home.signIn

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gabrielbmoro.crazymath.domain.usecase.SaveTokenUseCase
import com.gabrielbmoro.crazymath.domain.usecase.SignUpUseCase
import com.gabrielbmoro.crazymath.repository.CrazyMathRepository
import com.gabrielbmoro.crazymath.domain.usecase.SignInUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times

class SignInAlertViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: SignInViewModel
    private val repository: CrazyMathRepository = mock(CrazyMathRepository::class.java)
    private val signUpUseCase = SignUpUseCase(repository)
    private val saveTokenUseCase = SaveTokenUseCase(repository)
    private val loginUseCase = SignInUseCase(repository)

    @Before
    fun init() {
        viewModel = SignInViewModel(
                saveTokenUseCase = saveTokenUseCase,
                signUpUseCase = signUpUseCase,
                loginUseCase = loginUseCase
        )
    }

    @Test
    fun `when the user has a valid email`() {
        // arrange
        val email = "tido4410@gmail.com"

        GlobalScope.launch {
            // act
            viewModel.signIn(email)

            // assert
            Mockito.verify(repository, times(1)).signIn(email)
        }
    }

    @Test
    fun `when the user doesn't have a valid email`() {
        // arrange
        val email = "tido441ail.com"

        GlobalScope.launch {
            // act
            viewModel.signIn(email)

            // assert
            Mockito.verify(repository, times(0)).signUp(email)
        }
    }
}