package com.gabrielbmoro.crazymath.presentation.components

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.gabrielbmoro.crazymath.R

typealias StopwatchFinishAction = (() -> Unit)

class StopwatchComponent @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr), LifecycleObserver {

    private var countDownTimer: CountDownTimer? = null
    private var current: Long = DEFAULT_FROM
    private var callbackWhenFinish: StopwatchFinishAction? = null
    private var lifecycle: Lifecycle? = null

    init {
        updateTextAccordingToFromField()
    }

    fun start(onFinish: StopwatchFinishAction, lifecycle: Lifecycle) {
        this.lifecycle = lifecycle

        callbackWhenFinish = onFinish

        releaseCountDownTimer()

        countDownTimer = createCountDownTimer()

        countDownTimer?.start()
    }

    private fun createCountDownTimer(): CountDownTimer {
        return object : CountDownTimer(current * 1000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                current = (millisUntilFinished / 1000L)
                updateTextAccordingToFromField()
            }

            override fun onFinish() {
                current = 0
                updateTextAccordingToFromField()
                callbackWhenFinish?.invoke()
            }
        }
    }

    fun releaseCountDownTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
    }

    fun reset() {
        current = DEFAULT_FROM
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun updateTextAccordingToFromField() {
        if (this.lifecycle?.currentState?.isAtLeast(Lifecycle.State.RESUMED) == true) {
            text = context.getString(R.string.stopwatch_seconds, current)
        }
    }

    fun currentTimeInSeconds(): Long = current

    companion object {
        private const val DEFAULT_FROM = 30L
    }

}