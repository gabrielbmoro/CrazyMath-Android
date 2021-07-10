package com.gabrielbmoro.crazymath.domain.usecase

import com.gabrielbmoro.crazymath.repository.CrazyMathRepository

open class GetTokenUseCase constructor(
        private val repository: CrazyMathRepository
) {

    fun execute() = repository.getToken()
}