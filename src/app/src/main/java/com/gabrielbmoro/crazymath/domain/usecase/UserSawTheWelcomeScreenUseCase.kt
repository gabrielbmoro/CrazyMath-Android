package com.gabrielbmoro.crazymath.domain.usecase

import com.gabrielbmoro.crazymath.repository.CrazyMathRepository

class UserSawTheWelcomeScreenUseCase(private val repository: CrazyMathRepository) {

    fun execute() {
        repository.sawTheWelcomeScreen()
    }
}