package com.gabrielbmoro.crazymath.presentation.extensions

import android.view.View
import android.widget.ProgressBar
import com.gabrielbmoro.crazymath.core.Event

fun ProgressBar.changeStateFrom(event: Event<Boolean>) {
    visibility = if (event.peekContent())
        View.VISIBLE
    else View.GONE
}