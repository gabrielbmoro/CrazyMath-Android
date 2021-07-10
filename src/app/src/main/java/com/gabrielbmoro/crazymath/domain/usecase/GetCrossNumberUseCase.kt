package com.gabrielbmoro.crazymath.domain.usecase

import com.gabrielbmoro.crazymath.repository.CrazyMathRepository
import com.gabrielbmoro.crazymath.domain.model.GridElementGenerator
import com.gabrielbmoro.crazymath.domain.model.UserLevel

class GetCrossNumberUseCase(private val repository: CrazyMathRepository) {

    fun execute(userLevel: UserLevel): List<List<String>> {
        val gridGenerator = GridElementGenerator()

        val crossNumberConfigurations = repository.getCrossNumberConfigurations()

        return gridGenerator.createConfigurationFrom(userLevel, crossNumberConfigurations)
    }
}