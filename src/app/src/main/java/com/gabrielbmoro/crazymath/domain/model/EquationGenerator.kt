package com.gabrielbmoro.crazymath.domain.model

import java.util.ArrayList

object EquationGenerator {

    private val operations = OperationType.values()
    private val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, -1, -2, -3, -4, -5)

    fun getEquationList(): ArrayList<String> {
        val num1 = numbers.random()
        val num2 = numbers.random()
        val operation = operations.random()
        val result = getResultFrom(num1, num2, operation)
        return ArrayList<String>().apply {
            add(num1.toString())
            add(operation.value)
            add(num2.toString())
            add(result.toString())
        }
    }

    private fun getResultFrom(op1: Int, op2: Int, operation: OperationType): Int {
        return when (operation) {
            OperationType.MULT -> (op1 * op2)
            OperationType.SUB -> (op1 - op2)
            OperationType.SUM -> (op1 + op2)
        }
    }

    fun randomElement(availableOperations: Array<OperationType> = operations): String {
        return listOf(
                availableOperations.random().value,
                numbers.random().toString(),
                numbers.random().toString(),
                numbers.random().toString()
        ).random()
    }
}