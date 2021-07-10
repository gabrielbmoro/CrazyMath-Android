package com.gabrielbmoro.crazymath.domain.model

import com.google.gson.annotations.SerializedName

data class PreviewConfig(
        @SerializedName("easy")
        val easy: GridPreview,
        @SerializedName("medium")
        val medium: GridPreview,
        @SerializedName("hard")
        val hard: GridPreview
)

data class GridPreview(
        @SerializedName("exampleFirstEquationIndex")
        val exampleFirstEquationIndex: Int,
        @SerializedName("exampleFirstEquationOrientation")
        val exampleFirstEquationOrientation: Int,
        @SerializedName("rows")
        val rows: List<List<String>>
) {

    fun orientation() = Orientation.from(exampleFirstEquationOrientation)

    enum class Orientation(val value: Int) {
        HORIZONTAL(0),
        VERTICAL(1),
        VERTICAL_INVERSE(2);

        companion object {
            fun from(value: Int) = values().firstOrNull { it.value == value }
        }
    }
}

