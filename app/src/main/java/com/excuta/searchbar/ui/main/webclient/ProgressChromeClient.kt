package com.excuta.searchbar.ui.main.webclient

import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ProgressBar

class ProgressChromeClient(private val progressBar: ProgressBar) : WebChromeClient() {

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        progressBar.progress = newProgress
        super.onProgressChanged(view, newProgress)
    }
}