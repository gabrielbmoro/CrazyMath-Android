package com.gabrielbmoro.crazymath.presentation.components.preview

import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.gabrielbmoro.crazymath.R
import com.gabrielbmoro.crazymath.presentation.components.grid.GridViewComponent
import com.gabrielbmoro.crazymath.presentation.extensions.TranslationType
import com.gabrielbmoro.crazymath.presentation.extensions.arriveAnimation
import kotlinx.coroutines.*

class TutorialAnimator(
        private val baseFrameLayout: FrameLayout,
        private val gridViewComponent: GridViewComponent,
        private val tvPreviewDescription: TextView
) {

    private var animationJob: Job? = null

    private fun firstMovement(hand: ImageView) {
        hand.setImageDrawable(ContextCompat.getDrawable(gridViewComponent.context, R.drawable.ic_finger))
        baseFrameLayout.addView(hand)
    }

    private fun secondMovement() {
        tvPreviewDescription.isVisible = true
        tvPreviewDescription.setText(R.string.long_press)
    }

    private fun thirdMovement(hand: ImageView) {
        hand.setImageDrawable(ContextCompat.getDrawable(gridViewComponent.context, R.drawable.ic_finger_pressing))
    }

    private fun fourthMovement() {
        tvPreviewDescription.setText(R.string.start_dragging)
    }

    private fun fifthMovement(hand: ImageView, orientationCode: Int) {
        var value = gridViewComponent.getElementWidth() * 4
        when (orientationCode) {
            GridViewComponent.ORIENTATION_VERTICAL_INVERSE -> {
                value *= -1
                TranslationType.TRANSLATION_Y
            }
            GridViewComponent.ORIENTATION_VERTICAL -> TranslationType.TRANSLATION_Y
            GridViewComponent.ORIENTATION_HORIZONTAL -> TranslationType.TRANSLATION_X
            else -> null
        }?.let { movementType ->
            hand.arriveAnimation(ARRIVE_HAND_ANIMATION_TUTORIAL, value, movementType) {
                hand.setImageDrawable(ContextCompat.getDrawable(gridViewComponent.context, R.drawable.ic_finger))
            }
        }
    }

    private fun sixthMovement(hand: ImageView, startIndex: Int, orientationCode: Int) {
        tvPreviewDescription.isVisible = false
        baseFrameLayout.removeView(hand)
        gridViewComponent.selectRow(startIndex, orientationCode)
    }

    fun start(startIndex: Int, orientationCode: Int) {
        if (animationJob == null || animationJob?.isCompleted == true) {
            animationJob = GlobalScope.launch {
                gridViewComponent.getElementPosition(startIndex)?.let { startPosition ->
                    val handSize = gridViewComponent.context.resources.getDimensionPixelSize(R.dimen.hand_size)

                    val params = FrameLayout.LayoutParams(handSize, handSize)
                    params.leftMargin = startPosition.x
                    params.topMargin = startPosition.y

                    val hand = ImageView(gridViewComponent.context).apply {
                        layoutParams = params
                    }

                    withContext(Dispatchers.Main) {
                        firstMovement(hand)
                        secondMovement()
                    }
                    delay(500L)
                    withContext(Dispatchers.Main) {
                        thirdMovement(hand)
                    }
                    delay(500L)
                    withContext(Dispatchers.Main) {
                        fourthMovement()
                    }
                    delay(500L)
                    withContext(Dispatchers.Main) {
                        fifthMovement(hand, orientationCode)
                    }
                    delay(ARRIVE_HAND_ANIMATION_TUTORIAL + 1000L)
                    withContext(Dispatchers.Main) {
                        sixthMovement(hand, startIndex, orientationCode)
                    }
                }
            }
            animationJob?.start()
        }
    }

    companion object {
        private const val ARRIVE_HAND_ANIMATION_TUTORIAL = 2000L
    }
}