package com.gabrielbmoro.crazymath.core.extensions

fun String.isEmailValid() = "[a-z]+[0-9]*@[a-z]+.com".toRegex().matches(this)