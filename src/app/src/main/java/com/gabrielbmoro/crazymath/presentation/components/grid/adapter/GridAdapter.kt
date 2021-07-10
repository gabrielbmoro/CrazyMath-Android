package com.gabrielbmoro.crazymath.presentation.components.grid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.gabrielbmoro.crazymath.databinding.ViewHolderNumberCelBinding
import com.gabrielbmoro.crazymath.databinding.ViewHolderPreviewNumberCelBinding
import com.gabrielbmoro.crazymath.presentation.components.grid.GridViewComponent
import com.gabrielbmoro.crazymath.presentation.extensions.getOperationType
import java.util.concurrent.locks.ReentrantLock

typealias OnEquationResultEvent = ((Boolean) -> Unit)

class GridAdapter : RecyclerView.Adapter<GridViewHolder>(), EquationSelection {

    private var isPreviewMode: Boolean = false
    private val items = ArrayList<GridCell>()
    private val lockEquationEvaluation = ReentrantLock()
    private var equationSelectedEvent: OnEquationResultEvent? = null

    fun setup(grid: List<GridCell>, isPreview: Boolean, callback: OnEquationResultEvent?) {
        equationSelectedEvent = callback
        isPreviewMode = isPreview
        items.clear()
        items.addAll(grid)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val binding: ViewBinding = if (isPreviewMode)
            ViewHolderPreviewNumberCelBinding.inflate(LayoutInflater.from(parent.context))
        else ViewHolderNumberCelBinding.inflate(LayoutInflater.from(parent.context))

        return GridViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(
            holder: GridViewHolder,
            position: Int
    ) {
        holder.bind(
                items[position]
        )
    }

    override fun onSelectEffect(index: Int) {
        items[index].select()
        notifyItemChanged(index)
    }

    override fun onUnSelectEffect(index: Int) {
        items[index].unSelect()
        notifyItemChanged(index)
    }

    private fun markAsCorrect(vararg indexes: Int) {
        indexes.map { index ->
            items[index].correct()
            notifyItemChanged(index)
        }
    }

    override fun onSendAttempt(indexes: Array<Int>) {
        lockEquationEvaluation.lock()

        val indexOp1 = indexes[0]
        val indexOperator = indexes[1]
        val indexOp2 = indexes[2]
        val indexResult = indexes[3]

        val op1 = items[indexOp1].value
        val operator = items[indexOperator].value.getOperationType()
        val op2 = items[indexOp2].value
        val result = items[indexResult].value

        val isValid = EquationHandler.isValid(
                op1 = op1,
                op2 = op2,
                operation = operator,
                result = result
        )
        if (isValid) {
            markAsCorrect(indexOp1, indexOperator, indexOp2, indexResult)
        } else {
            resetAttempt(
                    listOfNotNull(indexOp1, indexOperator, indexOp2, indexResult)
                            .toTypedArray()
            )
        }
        equationSelectedEvent?.invoke(isValid)
        lockEquationEvaluation.unlock()
    }

    override fun isUnlockedToNextAttempt(): Boolean {
        return !lockEquationEvaluation.isLocked
    }

    override fun resetAttempt(indexes: Array<Int>) {
        indexes.map {
            if (it != INVALID_INDEX)
                onUnSelectEffect(it)
        }
    }

    fun selectEffect(firstPosition: Int, orientationCode: Int) {
        when (orientationCode) {
            GridViewComponent.ORIENTATION_HORIZONTAL -> 1
            GridViewComponent.ORIENTATION_VERTICAL_INVERSE -> -6
            GridViewComponent.ORIENTATION_VERTICAL -> 6
            else -> null
        }?.let { offset ->
            val secondPosition = firstPosition + offset
            val thirdPosition = secondPosition + offset
            val fourthPosition = thirdPosition + offset

            onSelectEffect(firstPosition)
            onSelectEffect(secondPosition)
            onSelectEffect(thirdPosition)
            onSelectEffect(fourthPosition)
            onSendAttempt(arrayOf(firstPosition, secondPosition, thirdPosition, fourthPosition))
        }
    }

    companion object {
        private const val INVALID_INDEX = -1
    }
}
