package com.gabrielbmoro.crazymath.presentation.extensions

import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import com.gabrielbmoro.crazymath.R
import com.gabrielbmoro.crazymath.core.extensions.isEmailValid
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.transformToBeAnEmailField() {
    hint = context.getString(R.string.email)
    editText?.apply {
        inputType = EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        doAfterTextChanged {
            val email = it.toString()
            this@transformToBeAnEmailField.error = if (email.isNotEmpty() && !email.isEmailValid())
                context.getString(R.string.email_is_not_valid_error_message)
            else null
        }
    }
}