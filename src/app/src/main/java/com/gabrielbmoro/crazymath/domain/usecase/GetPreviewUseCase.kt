package com.gabrielbmoro.crazymath.domain.usecase

import com.gabrielbmoro.crazymath.repository.CrazyMathRepository
import com.gabrielbmoro.crazymath.domain.model.GridElementGenerator
import com.gabrielbmoro.crazymath.domain.model.GridPreview
import com.gabrielbmoro.crazymath.domain.model.UserLevel

class GetPreviewUseCase constructor(
        private val repository: CrazyMathRepository
) {

    fun execute(userLevel: UserLevel) : GridPreview {
        val gridGenerator = GridElementGenerator()

        val preview = repository.getPreview()

        return gridGenerator.createConfigurationFrom(userLevel, preview)
    }
}