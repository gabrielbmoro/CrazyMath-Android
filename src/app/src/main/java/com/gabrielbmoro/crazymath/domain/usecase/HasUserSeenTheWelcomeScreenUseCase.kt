package com.gabrielbmoro.crazymath.domain.usecase

import com.gabrielbmoro.crazymath.repository.CrazyMathRepository

class HasUserSeenTheWelcomeScreenUseCase(private val repository: CrazyMathRepository) {

    fun execute() : Boolean = repository.hasUserSeenTheWelcomeScreenUseCase()
}