package com.gabrielbmoro.crazymath.presentation.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import com.gabrielbmoro.crazymath.R

class NavoImageButtonComponent @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageButton(context, attrs, defStyleAttr) {

    init {
        background = ContextCompat.getDrawable(context, R.drawable.primary_base_button_effect)
        setImageResource(R.drawable.ic_face_logo)
        scaleType = ScaleType.FIT_XY
        contentDescription = resources.getString(R.string.button_to_access_navo_assistant)
    }

}