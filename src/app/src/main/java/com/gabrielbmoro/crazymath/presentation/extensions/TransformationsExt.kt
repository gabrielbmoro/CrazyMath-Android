package com.gabrielbmoro.crazymath.presentation.extensions

import com.gabrielbmoro.crazymath.domain.model.OperationType
import com.gabrielbmoro.crazymath.presentation.components.grid.adapter.GridCell

fun List<List<String>>.convertRowsToGridCells(): List<GridCell> {
    return flatMap { lst ->
        lst.map { value ->
            val operationType = value.getOperationType()
            GridCell(
                    value = value,
                    textValue = value,
                    contentDescription = operationType?.value ?: value
            )
        }
    }.toList()
}

fun String.getOperationType(): OperationType? {
    return when (this) {
        OperationType.SUM.value -> OperationType.SUM
        OperationType.SUB.value -> OperationType.SUB
        OperationType.MULT.value -> OperationType.MULT
        else -> null
    }
}