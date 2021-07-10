package com.gabrielbmoro.crazymath.domain.usecase

import com.gabrielbmoro.crazymath.repository.CrazyMathRepository

class GetRankPositionUseCase constructor(
        private val repository: CrazyMathRepository
) {
    suspend fun execute(userPoints: Long): Int {
        val points = repository.getPoints()
        return points.sortedDescending().indexOfFirst { it < userPoints }
    }
}