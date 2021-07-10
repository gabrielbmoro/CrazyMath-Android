package com.gabrielbmoro.crazymath.presentation.components.grid.adapter

interface EquationSelection {

    fun onSelectEffect(index: Int)

    fun onUnSelectEffect(index: Int)

    fun onSendAttempt(indexes : Array<Int>)

    fun isUnlockedToNextAttempt(): Boolean

    fun resetAttempt(indexes : Array<Int>)
}