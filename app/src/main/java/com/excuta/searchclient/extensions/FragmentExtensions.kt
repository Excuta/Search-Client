package com.excuta.searchclient.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.excuta.searchclient.App

fun Fragment.hideKeyboard() {
    val systemService =
        this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    systemService.hideSoftInputFromWindow(this.view?.windowToken, 0)
}

fun Fragment.getApplication(): App? {
    return this.activity?.application as? App
}