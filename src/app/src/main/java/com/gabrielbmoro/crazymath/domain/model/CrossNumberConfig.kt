package com.gabrielbmoro.crazymath.domain.model

import com.google.gson.annotations.SerializedName

data class CrossNumberConfig(
        @SerializedName("data")
        val data: List<List<String>>,
        @SerializedName("userLevel")
        val userLevel: Int
)