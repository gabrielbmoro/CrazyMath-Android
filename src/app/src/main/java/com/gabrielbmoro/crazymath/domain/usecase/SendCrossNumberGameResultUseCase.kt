package com.gabrielbmoro.crazymath.domain.usecase

import com.gabrielbmoro.crazymath.repository.CrazyMathRepository
import java.util.Calendar

class SendCrossNumberGameResultUseCase(private val repository: CrazyMathRepository) {

    suspend fun execute(points: Long): Boolean {
        return repository.sendCrossNumberResult(
                firebaseUID = repository.getUserId() ?: "",
                points = points,
                timestamp = Calendar.getInstance().timeInMillis
        )
    }
}