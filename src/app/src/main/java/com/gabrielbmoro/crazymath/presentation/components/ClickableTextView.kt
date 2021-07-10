package com.gabrielbmoro.crazymath.presentation.components

import android.content.Context
import android.content.res.ColorStateList
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.gabrielbmoro.crazymath.R

typealias ClickableView = (() -> Unit)

class ClickableTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        movementMethod = LinkMovementMethod.getInstance()
        setLinkTextColor(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorAccent)))
    }

    fun setLinks(links: List<Pair<String, ClickableView>>) {
        val spannable = SpannableString(text)

        links.forEach { link ->
            val start = text.indexOf(link.first)
            val end = start + link.first.length
            spannable.setSpan(
                    object : ClickableSpan() {
                        override fun onClick(widget: View) {
                            link.second.invoke()
                        }
                    },
                    start,
                    end,
                    SpannableString.SPAN_INCLUSIVE_INCLUSIVE
            )
        }
        text = spannable
    }

}