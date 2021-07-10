package com.gabrielbmoro.crazymath.domain.model

import androidx.annotation.DrawableRes
import com.gabrielbmoro.crazymath.R

enum class OperationType(val value: String, @DrawableRes val icon: Int) {
    SUM("+", R.drawable.ic_add_black),
    SUB("-", R.drawable.ic_remove_black),
    MULT("X", R.drawable.ic_clear_black);
}