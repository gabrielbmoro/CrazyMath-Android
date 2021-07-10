package com.gabrielbmoro.crazymath.presentation.components.levelSlider

import android.content.Context
import android.util.AttributeSet
import com.gabrielbmoro.crazymath.R
import com.google.android.material.slider.LabelFormatter
import com.google.android.material.slider.Slider

/**
 * It is required to add the following lines:
 * - style="@style/Widget.MaterialComponents.Slider"
 * - android:theme="@style/Theme.MaterialComponents.Light"
 */

typealias LevelChangeValueCallback = (LevelValue) -> Unit

class LevelSliderComponent @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Slider(context, attrs, defStyleAttr) {

    private val easyLevel = LevelValue(R.string.easy, EASY_LEVEL_VALUE)
    private val mediumLevel = LevelValue(R.string.medium, MEDIUM_LEVEL_VALUE)
    private val hardLevel = LevelValue(R.string.hard, HARD_LEVEL_VALUE)

    var currentLevel: LevelValue = easyLevel
        private set

    init {
        value = VALUE_FROM
        valueFrom = VALUE_FROM
        valueTo = VALUE_TO
        stepSize = STEP_SIZE
        labelBehavior = LabelFormatter.LABEL_GONE
    }

    fun setListener(callback: LevelChangeValueCallback) {
        addOnChangeListener { _, value, _ ->
            refreshCurrentLevel(value)
            contentDescription = context.getString(currentLevel.levelDescription)
            callback.invoke(currentLevel)
        }
    }

    private fun refreshCurrentLevel(value: Float) {
        currentLevel = when (value) {
            EASY_LEVEL_VALUE -> easyLevel
            MEDIUM_LEVEL_VALUE -> mediumLevel
            HARD_LEVEL_VALUE -> hardLevel
            else -> currentLevel
        }
    }

    companion object {
        private const val STEP_SIZE = 1f
        private const val VALUE_FROM = 1f
        private const val VALUE_TO = 3f

        const val EASY_LEVEL_VALUE = 1f
        const val MEDIUM_LEVEL_VALUE = 2f
        const val HARD_LEVEL_VALUE = 3f
    }

}