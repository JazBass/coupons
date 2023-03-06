package com.jazbass.coupons.common.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.jazbass.coupons.R

fun validateTextCode(code: String): Boolean {
    return !(code.length < 5 || code.length > 10)
}

fun getMsgErrorByCode(errorCode: String?): Int = when (errorCode) {
    Constants.ERROR_EXIST -> R.string.error_unique_code
    Constants.ERROR_LENGTH -> R.string.error_invalid_length
    else -> R.string.error_invalid_unknown
}

fun hideKeyword(context: Context, view: View){
    val imn = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imn?.hideSoftInputFromWindow(view.windowToken, 0)
}
