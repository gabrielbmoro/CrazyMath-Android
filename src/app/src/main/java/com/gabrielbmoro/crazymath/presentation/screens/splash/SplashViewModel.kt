package com.gabrielbmoro.crazymath.presentation.screens.splash

import androidx.lifecycle.ViewModel
import com.gabrielbmoro.crazymath.helpers.navigation.NavigationController
import com.gabrielbmoro.crazymath.helpers.navigation.NavigationEvent
import com.gabrielbmoro.crazymath.domain.usecase.HasUserSeenTheWelcomeScreenUseCase
import com.gabrielbmoro.crazymath.domain.usecase.UserSawTheWelcomeScreenUseCase

class SplashViewModel constructor(
        private val navigator: NavigationController,
        private val hasUserSeenTheWelcomeScreenUseCase: HasUserSeenTheWelcomeScreenUseCase,
        private val userSawTheWelcomeScreenUseCase: UserSawTheWelcomeScreenUseCase
) : ViewModel() {

    fun shouldUserSeeTheMessageOrNotAndSetThatHeSaw(): Boolean {
        return if (hasUserSeenTheWelcomeScreenUseCase.execute()) {
            navigator.dispatchEvent(NavigationEvent.GO_TO_HOME_SCREEN)
            false
        } else {
            userSawTheWelcomeScreenUseCase.execute()
            true
        }
    }

    fun goToHomeScreen() {
        navigator.dispatchEvent(NavigationEvent.GO_TO_HOME_SCREEN)
    }
}