package com.gabrielbmoro.crazymath.domain.model

import com.gabrielbmoro.crazymath.presentation.components.grid.adapter.EquationHandler
import org.junit.Test
import com.google.common.truth.Truth.*

class EquationHandlerTest {

    @Test
    fun `sum operation - checkIfItsCorrect - correct`() {
        // arrange
        val op1 = "1"
        val op2 = "4"
        val operation = OperationType.SUM
        val result = "5"

        // act
        val isCorrect = EquationHandler.isValid(
                op1 = op1,
                op2 = op2,
                operation = operation,
                result = result
        )

        // assert
        assertThat(isCorrect).isTrue()
    }

    @Test
    fun `sum operation - checkIfItsCorrect - false`() {
        // arrange
        val op1 = "5"
        val op2 = "2"
        val operation = OperationType.SUM
        val result = "6"

        // act
        val isCorrect = EquationHandler.isValid(
                op1 = op1,
                op2 = op2,
                operation = operation,
                result = result
        )

        // assert
        assertThat(isCorrect).isFalse()
    }

    @Test
    fun `sub operation - checkIfItsCorrect - true`() {
        // arrange
        val op1 = "5"
        val op2 = "2"
        val operation = OperationType.SUB
        val result = "3"

        // act
        val isCorrect = EquationHandler.isValid(
                op1 = op1,
                op2 = op2,
                operation = operation,
                result = result
        )

        // assert
        assertThat(isCorrect).isTrue()
    }

    @Test
    fun `sub operation - checkIfItsCorrect - false`() {
        // arrange
        val op1 = "8"
        val op2 = "12"
        val operation = OperationType.SUB
        val result = "-3"

        // act
        val isCorrect = EquationHandler.isValid(
                op1 = op1,
                op2 = op2,
                operation = operation,
                result = result
        )

        // assert
        assertThat(isCorrect).isFalse()
    }

    @Test
    fun `mult operation - checkIfItsCorrect - true`() {
        // arrange
        val op1 = "8"
        val op2 = "2"
        val operation = OperationType.MULT
        val result = "16"

        // act
        val isCorrect = EquationHandler.isValid(
                op1 = op1,
                op2 = op2,
                operation = operation,
                result = result
        )

        // assert
        assertThat(isCorrect).isTrue()
    }

    @Test
    fun `mult operation - checkIfItsCorrect - false`() {
        // arrange
        val op1 = "0"
        val op2 = "1"
        val operation = OperationType.MULT
        val result = "1"

        // act
        val isCorrect = EquationHandler.isValid(
                op1 = op1,
                op2 = op2,
                operation = operation,
                result = result
        )

        // assert
        assertThat(isCorrect).isFalse()
    }
}