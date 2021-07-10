package com.gabrielbmoro.crazymath.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.gabrielbmoro.crazymath.R
import com.gabrielbmoro.crazymath.databinding.ComponentNavoTalkBinding
import com.gabrielbmoro.crazymath.presentation.extensions.arriveAnimation
import com.gabrielbmoro.crazymath.presentation.extensions.balloonAnimation

class NavoTalkComponent @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ComponentNavoTalkBinding.inflate(
            LayoutInflater.from(context), this, true
    )

    fun setup(text: String, links: List<Pair<String, ClickableView>> = emptyList()) {
        binding.navoMessage.text = text
        binding.navoMessage.setLinks(links)

        startAnimation()
    }

    private fun startAnimation() {
        binding.navoIcon.arriveAnimation(
                animationDuration = ASSISTANT_MOVEMENT_DURATION_EFFECT,
                value = resources.getDimensionPixelSize(R.dimen.back_margin_start).toFloat()
        )

        binding.navoMessage.balloonAnimation()
    }

    companion object {
        private const val ASSISTANT_MOVEMENT_DURATION_EFFECT = 500L
    }
}