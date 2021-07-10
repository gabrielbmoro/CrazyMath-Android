package com.gabrielbmoro.crazymath

import org.mockito.Mockito

// Reference at: https://dev.to/arthlimchiu/how-to-unit-test-livedata-and-viewmodel-5h7f
inline fun <reified T> mockObserver(): T = Mockito.mock(T::class.java)