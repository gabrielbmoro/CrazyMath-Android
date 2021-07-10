package com.gabrielbmoro.crazymath.presentation.components.grid.adapter

import com.gabrielbmoro.crazymath.domain.model.OperationType

object EquationHandler {

    fun isValid(op1: String, op2: String, result: String, operation: OperationType?): Boolean {
        val firstOperatorValue = op1.toIntOrNull()
        val secondOperatorValue = op2.toIntOrNull()
        val resultValue = result.toIntOrNull()

        return if (firstOperatorValue != null && secondOperatorValue != null && resultValue != null) {
            when (operation) {
                OperationType.SUB -> (firstOperatorValue - secondOperatorValue) == resultValue
                OperationType.MULT -> (firstOperatorValue * secondOperatorValue) == resultValue
                OperationType.SUM -> (firstOperatorValue + secondOperatorValue) == resultValue
                else -> false
            }
        } else
            false
    }
}