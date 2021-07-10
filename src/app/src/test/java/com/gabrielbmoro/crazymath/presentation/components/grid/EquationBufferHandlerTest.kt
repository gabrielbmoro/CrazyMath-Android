package com.gabrielbmoro.crazymath.presentation.components.grid

import com.gabrielbmoro.crazymath.presentation.components.grid.EquationBufferHandler.Companion.INVALID_POSITION
import org.junit.Test
import com.google.common.truth.Truth.*

class EquationBufferHandlerTest {

    @Test
    fun `buffer has indexes - reset the buffer - indexes with -1 value`() {
        // arrange
        val equationBufferHandler = EquationBufferHandler()
        equationBufferHandler.equationAttemptBufferPositions[0] = 1
        equationBufferHandler.equationAttemptBufferPositions[1] = 2
        equationBufferHandler.equationAttemptBufferPositions[2] = 3
        equationBufferHandler.equationAttemptBufferPositions[3] = 4

        // act
        equationBufferHandler.resetAttemptBuffer()

        // assert
        assertThat(equationBufferHandler.equationAttemptBufferPositions[0]).isEqualTo(INVALID_POSITION)
        assertThat(equationBufferHandler.equationAttemptBufferPositions[1]).isEqualTo(INVALID_POSITION)
        assertThat(equationBufferHandler.equationAttemptBufferPositions[2]).isEqualTo(INVALID_POSITION)
        assertThat(equationBufferHandler.equationAttemptBufferPositions[3]).isEqualTo(INVALID_POSITION)
    }

    @Test
    fun `buffer has valid indexes - valid attempt - true`() {
        // arrange
        val equationBufferHandler = EquationBufferHandler()
        equationBufferHandler.equationAttemptBufferPositions[0] = 11
        equationBufferHandler.equationAttemptBufferPositions[1] = 10
        equationBufferHandler.equationAttemptBufferPositions[2] = 12
        equationBufferHandler.equationAttemptBufferPositions[3] = 13

        // act
        val actual = equationBufferHandler.validAttempt()

        // assert
        assertThat(actual).isTrue()
    }

    @Test
    fun `buffer has one invalid index - valid attempt - false`() {
        // arrange
        val equationBufferHandler = EquationBufferHandler()
        equationBufferHandler.equationAttemptBufferPositions[0] = 11
        equationBufferHandler.equationAttemptBufferPositions[1] = INVALID_POSITION
        equationBufferHandler.equationAttemptBufferPositions[2] = 12
        equationBufferHandler.equationAttemptBufferPositions[3] = 13

        // act
        val actual = equationBufferHandler.validAttempt()

        // assert
        assertThat(actual).isFalse()
    }

    @Test
    fun `there is no slot available - isPossibleToSelect - false`() {
        // arrange
        val equationBufferHandler = EquationBufferHandler()
        equationBufferHandler.equationAttemptBufferPositions[0] = 11
        equationBufferHandler.equationAttemptBufferPositions[1] = 10
        equationBufferHandler.equationAttemptBufferPositions[2] = 12
        equationBufferHandler.equationAttemptBufferPositions[3] = 13

        // act
        val actual = equationBufferHandler.isPossibleToSelect(14)

        // assert
        assertThat(actual).isFalse()
    }

    @Test
    fun `there is slot available but the element is in the buffer - isPossibleToSelect - false`() {
        // arrange
        val equationBufferHandler = EquationBufferHandler()
        equationBufferHandler.equationAttemptBufferPositions[0] = 11
        equationBufferHandler.equationAttemptBufferPositions[1] = 12
        equationBufferHandler.equationAttemptBufferPositions[2] = 13
        equationBufferHandler.equationAttemptBufferPositions[3] = INVALID_POSITION

        // act
        val actual = equationBufferHandler.isPossibleToSelect(13)

        // assert
        assertThat(actual).isFalse()
    }

    @Test
    fun `there is slot available but the element is not in the buffer - isPossibleToSelect - true`() {
        // arrange
        val equationBufferHandler = EquationBufferHandler()
        equationBufferHandler.equationAttemptBufferPositions[0] = 11
        equationBufferHandler.equationAttemptBufferPositions[1] = 12
        equationBufferHandler.equationAttemptBufferPositions[2] = 13
        equationBufferHandler.equationAttemptBufferPositions[3] = INVALID_POSITION

        // act
        val actual = equationBufferHandler.isPossibleToSelect(10)

        // assert
        assertThat(actual).isTrue()
    }

    @Test
    fun `slot available - cache position - true`() {
        // arrange
        val equationBufferHandler = EquationBufferHandler()
        equationBufferHandler.equationAttemptBufferPositions[0] = 11
        equationBufferHandler.equationAttemptBufferPositions[1] = 12
        equationBufferHandler.equationAttemptBufferPositions[2] = 13
        equationBufferHandler.equationAttemptBufferPositions[3] = INVALID_POSITION

        // act
        val actual = equationBufferHandler.cachePosition(14)

        // assert
        assertThat(actual).isTrue()
    }

    @Test
    fun `slot not available - cache position - false`() {
        // arrange
        val equationBufferHandler = EquationBufferHandler()
        equationBufferHandler.equationAttemptBufferPositions[0] = 11
        equationBufferHandler.equationAttemptBufferPositions[1] = 12
        equationBufferHandler.equationAttemptBufferPositions[2] = 13
        equationBufferHandler.equationAttemptBufferPositions[3] = 14

        // act
        val actual = equationBufferHandler.cachePosition(15)

        // assert
        assertThat(actual).isFalse()
    }

    @Test
    fun `existent index - unCache position - true`() {
        // arrange
        val equationBufferHandler = EquationBufferHandler()
        equationBufferHandler.equationAttemptBufferPositions[0] = 11
        equationBufferHandler.equationAttemptBufferPositions[1] = 12
        equationBufferHandler.equationAttemptBufferPositions[2] = 13
        equationBufferHandler.equationAttemptBufferPositions[3] = 14

        // act
        val actual = equationBufferHandler.unCachePosition(14)

        // assert
        assertThat(actual).isTrue()
    }

    @Test
    fun `not existent index - unCache position - false`() {
        // arrange
        val equationBufferHandler = EquationBufferHandler()
        equationBufferHandler.equationAttemptBufferPositions[0] = 11
        equationBufferHandler.equationAttemptBufferPositions[1] = 12
        equationBufferHandler.equationAttemptBufferPositions[2] = 13
        equationBufferHandler.equationAttemptBufferPositions[3] = 14

        // act
        val actual = equationBufferHandler.unCachePosition(16)

        // assert
        assertThat(actual).isFalse()
    }

}