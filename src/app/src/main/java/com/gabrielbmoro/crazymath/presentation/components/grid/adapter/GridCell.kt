package com.gabrielbmoro.crazymath.presentation.components.grid.adapter

import androidx.annotation.ColorRes
import com.gabrielbmoro.crazymath.R

data class GridCell(
        var selectVisibility: Boolean = false,
        var correctVisibility: Boolean = false,
        val value: String,
        val textValue : String? = null,
        val contentDescription: String,
        @ColorRes var textColor: Int = R.color.colorPrimaryDark
){
    fun correct(){
        correctVisibility = true
        selectVisibility = false
        textColor = android.R.color.white
    }

    fun select(){
        selectVisibility = true
    }

    fun unSelect(){
        selectVisibility = false
    }
}