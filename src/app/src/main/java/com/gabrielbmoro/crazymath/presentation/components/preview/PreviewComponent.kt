package com.gabrielbmoro.crazymath.presentation.components.preview

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.gabrielbmoro.crazymath.databinding.ComponentPreviewBinding
import com.gabrielbmoro.crazymath.presentation.components.grid.adapter.GridCell


class PreviewComponent @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ComponentPreviewBinding = ComponentPreviewBinding.inflate(
            LayoutInflater.from(context), this, true
    )
    private val tutorialAnimator = TutorialAnimator(
            binding.gridFrameLayout,
            binding.gridPreviewComponent,
            binding.tvPreviewDescription
    )

    fun start(previewElements: List<GridCell>, startIndex: Int, orientationCode: Int) {
        binding.gridPreviewComponent.setupAsPreviewMode(6, previewElements)

        Handler(Looper.getMainLooper()).postDelayed({
            tutorialAnimator.start(startIndex = startIndex, orientationCode = orientationCode)
        }, 500L)
    }

}