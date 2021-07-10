package com.gabrielbmoro.crazymath.presentation.components

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.gabrielbmoro.crazymath.R

class GameButton @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatButton(context, attrs, defStyleAttr) {

    init {
        background = ContextCompat.getDrawable(context, R.drawable.secondary_base_button_effect)
    }

    fun setMode(enabled: Boolean, @StringRes res : Int, @DrawableRes startDrawableRes : Int?, listener: OnClickListener?){
        text = context.getString(res)
        setOnClickListener(listener)
        val startDrawable = startDrawableRes?.let {
            ContextCompat.getDrawable(context, startDrawableRes)
        }
        setCompoundDrawablesWithIntrinsicBounds(startDrawable, null, null, null)
        isEnabled = enabled
        invalidate()
    }

}