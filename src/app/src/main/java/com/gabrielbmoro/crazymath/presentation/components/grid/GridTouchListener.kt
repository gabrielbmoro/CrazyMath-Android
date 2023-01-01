package com.gabrielbmoro.crazymath.presentation.components.grid

import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView
import com.gabrielbmoro.crazymath.presentation.components.grid.adapter.EquationSelection
import timber.log.Timber

/**
 * The GridTouchListener will not work if is inside of a scroll view.
 */
class GridTouchListener(
        private val contract: EquationSelection,
        private val recyclerView: RecyclerView
) : RecyclerView.SimpleOnItemTouchListener(), GestureDetector.OnGestureListener {

    /**
     * The ACTION_MOVE is call a lot of times, to avoid these multiple events
     * we need to use the _lastIndex to avoid that. Just consider the single event.
     */
    private var lastMoveIndex = INVALID_POSITION
    private val equationHandler = EquationBufferHandler()
    private lateinit var gestureDetectorCompat: GestureDetectorCompat

    /**
     * 1. A long press event will unlock the user selection, the action up will lock again
     */
    private var _onTouchEventIsAvailable = false

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        // gesture detector initialization - the only way to detect the long press event
        if (!isGestureDetectorInitialized()) {
            gestureDetectorCompat = GestureDetectorCompat(rv.context, this@GridTouchListener)
        }
        gestureDetectorCompat.onTouchEvent(e)
        return _onTouchEventIsAvailable
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        // send the event to check if there is a long press event
        gestureDetectorCompat.onTouchEvent(e)

        val adapterPosition = getAdapterPosition(x = e.x, y = e.y, rv = rv)
        Timber.d("Action ${e.action} -----> position: $adapterPosition ")

        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                gestureDetectorCompat.setIsLongpressEnabled(true)
            }
            MotionEvent.ACTION_MOVE -> {
                Timber.d("Action move -----> position: $adapterPosition")
                onSelectElement(adapterPosition)
            }
            MotionEvent.ACTION_UP -> {
                Timber.d("Action up ----->")
                onSelectElement(adapterPosition)

                checkSizeAndSendAttempt()
            }
            else -> {
                Timber.d("other motion event -> $adapterPosition - event action is ${e.action}")
                gestureDetectorCompat.setIsLongpressEnabled(false)
            }
        }
    }

    private fun checkSizeAndSendAttempt() {
        if (equationHandler.validAttempt()) {
            contract.onSendAttempt(equationHandler.equationAttemptBufferPositions)
        } else {
            contract.resetAttempt(equationHandler.equationAttemptBufferPositions)
        }
    }

    private fun resetSelection() {
        lastMoveIndex = INVALID_POSITION
        equationHandler.resetAttemptBuffer()
        _onTouchEventIsAvailable = false
    }

    private fun onSelectElement(adapterPosition: Int) {
        if (contract.isUnlockedToNextAttempt() && lastMoveIndex != adapterPosition) {
            if (adapterPosition != INVALID_POSITION) {
                if (equationHandler.isPossibleToSelect(adapterPosition)) {
                    val isCached = equationHandler.cachePosition(adapterPosition)
                    if (isCached)
                        contract.onSelectEffect(adapterPosition)
                } else {
                    val isUnCached = equationHandler.unCachePosition(adapterPosition)
                    if (isUnCached)
                        contract.onUnSelectEffect(adapterPosition)
                }
                lastMoveIndex = adapterPosition
            }
        }
    }

    private fun isGestureDetectorInitialized() = ::gestureDetectorCompat.isInitialized

    private fun getAdapterPosition(x: Float, y: Float, rv: RecyclerView) =
            rv.findChildViewUnder(x, y)?.let { vw ->
                rv.getChildAdapterPosition(vw)
            } ?: INVALID_POSITION


    // region gesture_detector callbacks
    override fun onDown(p0: MotionEvent) = false

    override fun onFling(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float) = false

    override fun onLongPress(e: MotionEvent) {
        resetSelection()

        _onTouchEventIsAvailable = true

        val adapterPosition = getAdapterPosition(x = e.x, y = e.y, rv = recyclerView)
        onSelectElement(adapterPosition)
    }

    override fun onScroll(p0: MotionEvent, p1: MotionEvent, p2: Float, p3: Float) = false

    override fun onShowPress(p0: MotionEvent) {}

    override fun onSingleTapUp(p0: MotionEvent) = false
    // endregion

    companion object {
        private const val INVALID_POSITION = -1
    }
}
