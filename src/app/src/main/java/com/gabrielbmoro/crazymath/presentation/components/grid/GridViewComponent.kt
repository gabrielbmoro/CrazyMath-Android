package com.gabrielbmoro.crazymath.presentation.components.grid

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielbmoro.crazymath.presentation.components.grid.adapter.GridAdapter
import com.gabrielbmoro.crazymath.presentation.components.grid.adapter.GridCell
import com.gabrielbmoro.crazymath.presentation.components.grid.adapter.OnEquationResultEvent
import kotlin.math.roundToInt

class GridViewComponent @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val gridAdapter = GridAdapter()

    private fun setup(dimen: Int, grid: List<GridCell>, callback: OnEquationResultEvent?, isPreview: Boolean) {
        layoutManager = GridLayoutManager(
                context,
                dimen
        )
        itemAnimator = null
        setHasFixedSize(true)
        adapter = gridAdapter

        gridAdapter.setup(
                grid = grid,
                callback = callback,
                isPreview = isPreview
        )
    }

    fun setupAsPreviewMode(dimen: Int, grid: List<GridCell>) {
        setup(dimen = dimen, grid = grid, isPreview = true, callback = null)
    }

    fun setupAsGameZoneMode(dimen: Int, grid: List<GridCell>, callback: OnEquationResultEvent) {
        setup(dimen = dimen, grid = grid, isPreview = false, callback = callback)

        addOnItemTouchListener(
                GridTouchListener(
                        contract = gridAdapter,
                        recyclerView = this@GridViewComponent
                )
        )
    }

    fun setupAsEmptyStateMode() {
        val gridCell = ArrayList<GridCell>()
        val amountOfColumns = 6
        val amountOfLines = 5
        repeat(amountOfLines) {
            repeat(amountOfColumns) {
                gridCell.add(GridCell(
                        value = "",
                        textValue = null,
                        contentDescription = "",
                        textColor = android.R.color.white,
                        correctVisibility = false,
                        selectVisibility = false
                ))
            }
        }
        setup(dimen = amountOfColumns, grid = gridCell.toList(), callback = null, isPreview = false)
    }

    fun getElementPosition(index: Int): Point? {
        return findViewHolderForAdapterPosition(index)?.itemView?.let { vw ->
            val x = vw.x.roundToInt()
            val y = vw.y.roundToInt()
            Point(x, y)
        }
    }

    fun getElementWidth(): Float {
        return findViewHolderForAdapterPosition(0)?.itemView?.width?.toFloat() ?: 0f
    }

    /**
     * @param orientationCode it can be:
     * - ORIENTATION_HORIZONTAL,
     * - ORIENTATION_VERTICAL,
     * - ORIENTATION_VERTICAL_INVERSE.
     */
    fun selectRow(startIndex: Int, orientationCode: Int) {
        gridAdapter.selectEffect(startIndex, orientationCode)
    }

    companion object {
        const val ORIENTATION_HORIZONTAL = 0
        const val ORIENTATION_VERTICAL = 1
        const val ORIENTATION_VERTICAL_INVERSE = 2;
    }

}