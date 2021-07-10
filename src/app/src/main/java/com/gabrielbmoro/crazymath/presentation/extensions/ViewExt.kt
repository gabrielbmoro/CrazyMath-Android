package com.gabrielbmoro.crazymath.presentation.extensions

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import androidx.core.view.isVisible
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce

fun View.showFor(animationDuration: Long, onAnimationEndEvent: (() -> Unit)) {
    ValueAnimator.ofInt(0, 1, 2, 3, 4, 5).also { animator ->
        animator.duration = animationDuration
        animator.addUpdateListener { va ->
            if (va.animatedFraction == 1f) {
                isVisible = false
                onAnimationEndEvent()
            }
        }
    }.start()
}

fun View.arriveAnimation(animationDuration: Long, value: Float, animationType: TranslationType = TranslationType.TRANSLATION_X, onAnimationEndEvent: (() -> Unit)? = null) {
    ObjectAnimator.ofFloat(this@arriveAnimation, animationType.propertyName, value).apply {
        duration = animationDuration
        start()
        addUpdateListener { va ->
            if (va.animatedFraction == 1f) {
                onAnimationEndEvent?.invoke()
            }
        }
    }
}

fun View.balloonAnimation() {
    val aQuarterScreen = resources.displayMetrics?.widthPixels?.div(4f) ?: 0f
    val disclaimerWidth = aQuarterScreen * 2f
    layoutParams = layoutParams.apply {
        width = disclaimerWidth.toInt()
    }
    SpringAnimation(this@balloonAnimation, SpringAnimation.X).apply {
        spring = SpringForce().also { force ->
            force.finalPosition = aQuarterScreen
            force.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            force.stiffness = SpringForce.STIFFNESS_VERY_LOW
        }
    }.start()
}

enum class TranslationType(val propertyName: String) {
    TRANSLATION_Y("translationY"), TRANSLATION_X("translationX")
}