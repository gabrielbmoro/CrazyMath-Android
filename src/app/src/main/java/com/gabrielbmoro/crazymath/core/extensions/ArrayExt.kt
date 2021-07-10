package com.gabrielbmoro.crazymath.core.extensions

import java.lang.IndexOutOfBoundsException


fun <T> Array<Array<T>>.countTotalElements(): Int {
    var amountOfElements = 0
    this.forEach {
        amountOfElements += it.size
    }
    return amountOfElements
}

fun <T> Array<Array<T>>.safeSetValue(i: Int, j: Int, value: T) : Boolean {
    return if (i in this.indices && j in this[i].indices) {
        this[i][j] = value
        true
    } else false
}

fun <T> Array<Array<T>>.safeGetValue(i : Int, j : Int) : T? {
    return try {
        this[i][j]
    } catch(indexOutOfBoundsException : IndexOutOfBoundsException){
        null
    }
}