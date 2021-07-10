package com.gabrielbmoro.crazymath.domain.usecase

import com.gabrielbmoro.crazymath.repository.CrazyMathRepository

class SendUserFeedbackUseCase(private val repository: CrazyMathRepository) {

    suspend fun execute(userMessage: String) : Boolean {
        return repository.sendUsersFeedback(userMessage)
    }
}