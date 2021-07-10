package com.gabrielbmoro.crazymath.domain.usecase

import com.gabrielbmoro.crazymath.repository.CrazyMathRepository

open class SignUpUseCase(private val repository: CrazyMathRepository) {

    suspend fun execute(email: String): String? {
        return repository.signUp(email)
    }
}