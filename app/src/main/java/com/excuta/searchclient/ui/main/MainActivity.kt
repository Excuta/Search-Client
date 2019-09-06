package com.excuta.searchclient.ui.main

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.excuta.searchclient.R
import com.excuta.searchclient.ui.main.search.SearchFragment

class MainActivity : AppCompatActivity(), SearchFragment.SelectedSuggestionProvider {

    private val suggestionLiveData = MutableLiveData<String>()
    override fun getLiveData(): LiveData<String> {
        return suggestionLiveData
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        if (Intent.ACTION_SEARCH == intent?.action) {
            intent.getStringExtra(SearchManager.QUERY)?.let {
                suggestionLiveData.value = it
            }
        }
    }
}
