package com.gabrielbmoro.crazymath.domain.model

enum class UserLevel(val value: Int) {
    EASY(0),
    MEDIUM(1),
    HARD(2);

    companion object {
        fun fromValue(value: Int) = values().firstOrNull { it.value == value }
    }
}