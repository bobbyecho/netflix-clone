package com.example.shared.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.listen(
    beforeTextChanged: ((String) -> Unit)? = null,
    onTextchanged: ((String) -> Unit)? = null,
    afterTextChanged: ((String) -> Unit)? = null,
) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            beforeTextChanged?.invoke(s.toString())
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextchanged?.invoke(s.toString())
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged?.invoke(editable.toString())
        }
    })
}