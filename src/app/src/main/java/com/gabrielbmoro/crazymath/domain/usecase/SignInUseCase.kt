package com.gabrielbmoro.crazymath.domain.usecase

import com.gabrielbmoro.crazymath.repository.CrazyMathRepository

open class SignInUseCase(private val repository: CrazyMathRepository) {

    suspend fun execute(email: String) : String? {
        return repository.signIn(email = email)
    }
}