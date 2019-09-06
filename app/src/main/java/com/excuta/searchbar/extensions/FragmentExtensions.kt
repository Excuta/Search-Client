package com.excuta.searchbar.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    val systemService =
        this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    systemService.hideSoftInputFromWindow(this.view?.windowToken, 0)
}