package com.gabrielbmoro.crazymath.presentation.components.grid.adapter

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.gabrielbmoro.crazymath.databinding.ViewHolderNumberCelBinding
import com.gabrielbmoro.crazymath.databinding.ViewHolderPreviewNumberCelBinding

class GridViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cell: GridCell) {
        val tvNumber: TextView?
        val celCorrectView: View?
        val celSelectedView: View?

        when (binding) {
            is ViewHolderNumberCelBinding -> {
                tvNumber = binding.tvNumber
                celCorrectView = binding.celCorrectView
                celSelectedView = binding.celSelectedView
            }
            is ViewHolderPreviewNumberCelBinding -> {
                tvNumber = binding.tvNumber
                celCorrectView = binding.celCorrectView
                celSelectedView = binding.celSelectedView
            }
            else -> {
                tvNumber = null
                celCorrectView = null
                celSelectedView = null
            }
        }

        tvNumber?.text = cell.textValue
        tvNumber?.setTextColor(ContextCompat.getColor(binding.root.context, cell.textColor))
        celCorrectView?.isVisible = cell.correctVisibility
        celSelectedView?.isVisible = cell.selectVisibility
    }

}