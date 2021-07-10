package com.gabrielbmoro.crazymath.presentation.extensions

import android.animation.ValueAnimator
import android.widget.TextView
import kotlin.math.roundToInt

fun TextView.typeTheText(fullText: String, animationDuration: Long, onAnimationEndEvent: (() -> Unit)) {
    ValueAnimator.ofInt(0, 1, 2, 3, 4, 5).also {
        animator->
        animator.duration = animationDuration
        animator.addUpdateListener {
            va->
            val showUntilIndex = (va.animatedFraction * fullText.lastIndex).roundToInt()
            text = fullText.substring(0, showUntilIndex)
            if (va.animatedFraction == 1f)
                onAnimationEndEvent()
        }
    }.start()
}

