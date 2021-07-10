package com.gabrielbmoro.crazymath.presentation.components.grid

class EquationBufferHandler {

    private val _equationAttemptBufferPositions = Array(EQUATION_ATTEMPT_BUFFER_SIZE) {
        INVALID_POSITION
    }
    val equationAttemptBufferPositions
        get() = _equationAttemptBufferPositions

    fun resetAttemptBuffer() {
        _equationAttemptBufferPositions[0] = INVALID_POSITION
        _equationAttemptBufferPositions[1] = INVALID_POSITION
        _equationAttemptBufferPositions[2] = INVALID_POSITION
        _equationAttemptBufferPositions[3] = INVALID_POSITION
    }

    fun validAttempt(): Boolean {
        return _equationAttemptBufferPositions.none { it == INVALID_POSITION }
    }


    fun isPossibleToSelect(index: Int): Boolean {
        return !_equationAttemptBufferPositions.contains(index) && _equationAttemptBufferPositions.any { it == INVALID_POSITION }
    }

    fun cachePosition(position: Int): Boolean {
        val slotIndex = _equationAttemptBufferPositions.indexOfFirst { it == INVALID_POSITION }
        if (slotIndex != INVALID_POSITION) {
            _equationAttemptBufferPositions[slotIndex] = position
            return true
        }
        return false
    }

    fun unCachePosition(position: Int): Boolean {
        val targetIndex = _equationAttemptBufferPositions.indexOfFirst { it == position }
        if (targetIndex != INVALID_POSITION) {
            _equationAttemptBufferPositions[targetIndex] = INVALID_POSITION
            return true
        }
        return false
    }

    companion object {
        const val INVALID_POSITION = -1
        private const val EQUATION_ATTEMPT_BUFFER_SIZE = 4
    }
}