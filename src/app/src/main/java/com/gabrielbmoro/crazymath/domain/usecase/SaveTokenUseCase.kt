package com.gabrielbmoro.crazymath.domain.usecase

import com.gabrielbmoro.crazymath.repository.CrazyMathRepository

open class SaveTokenUseCase(private val repository: CrazyMathRepository) {

    fun execute(token: String): Boolean {
        return repository.saveToken(token)
    }
}