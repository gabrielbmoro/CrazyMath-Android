package com.gabrielbmoro.crazymath.helpers

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

class VibrationHandler {

    fun vibrate(context: Context, durationInMilli: Long) {
        (context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator)?.let { vibrator ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                vibrator.vibrate(VibrationEffect.createOneShot(durationInMilli, VibrationEffect.DEFAULT_AMPLITUDE))
            else vibrator.vibrate(durationInMilli)
        }
    }
}