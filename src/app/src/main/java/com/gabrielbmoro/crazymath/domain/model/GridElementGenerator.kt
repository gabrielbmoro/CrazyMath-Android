package com.gabrielbmoro.crazymath.domain.model

import java.util.ArrayList

class GridElementGenerator {

    private val equationA: ArrayList<String> = EquationGenerator.getEquationList()
    private val equationB: ArrayList<String> = EquationGenerator.getEquationList()
    private val equationC: ArrayList<String> = EquationGenerator.getEquationList()
    private val equationI: ArrayList<String> = EquationGenerator.getEquationList()

    fun createConfigurationFrom(level: UserLevel, configurations: List<CrossNumberConfig>): List<List<String>> {
        val configuration = configurations.filter { it.userLevel == level.value }.random()
        return configuration.data.map { row ->
            row.map { element ->
                when {
                    (element == RANDOM_ELEMENT) -> EquationGenerator.randomElement()
                    (element.matches(EQUATION_ELEMENT_REGEX_TO_COMPARE)) -> equationElement(element.first())
                    else -> element
                }
            }
        }
    }

    fun createConfigurationFrom(userLevel: UserLevel, preview: PreviewConfig): GridPreview {
        val configuration = when (userLevel) {
            UserLevel.EASY -> Pair(preview.easy, arrayOf(OperationType.SUM))
            UserLevel.MEDIUM -> Pair(preview.medium, arrayOf(OperationType.SUM, OperationType.SUB))
            UserLevel.HARD -> Pair(preview.hard, arrayOf(OperationType.SUM, OperationType.SUB, OperationType.MULT))
        }

        val rowsWithValidNumbers = configuration.first.rows.map { row ->
            row.map { element ->
                when {
                    (element == RANDOM_ELEMENT) -> EquationGenerator.randomElement(configuration.second)
                    (element.matches(EQUATION_ELEMENT_REGEX_TO_COMPARE)) -> equationElement(element.first())
                    else -> element
                }
            }
        }

        return GridPreview(
                exampleFirstEquationIndex = configuration.first.exampleFirstEquationIndex,
                exampleFirstEquationOrientation = configuration.first.exampleFirstEquationOrientation,
                rows = rowsWithValidNumbers
        )
    }


    private fun equationElement(code: Char): String {
        return when (code) {
            'a' -> pickFromEquation(equationA)
            'b' -> pickFromEquation(equationB)
            'c' -> pickFromEquation(equationC)
            'i' -> pickFromEquation(equationI, true)
            else -> ""
        }
    }

    private fun pickFromEquation(equation: ArrayList<String>, reverse: Boolean = false): String {
        return if (equation.isNotEmpty()) {
            val target = if (reverse)
                Pair(equation.last(), equation.lastIndex)
            else Pair(equation.first(), 0)

            if (equation.isNotEmpty())
                equation.removeAt(target.second)
            target.first
        } else ""
    }

    companion object {
        private val EQUATION_ELEMENT_REGEX_TO_COMPARE = Regex("[abci]")
        private const val RANDOM_ELEMENT = "X"
    }
}