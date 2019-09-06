package com.excuta.searchclient.data.suggestions

import android.content.SearchRecentSuggestionsProvider

class SuggestionsContentProvider : SearchRecentSuggestionsProvider() {

    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "com.excuta.searchclient.data.suggestions.SuggestionsContentProvider"
        const val MODE = DATABASE_MODE_QUERIES or DATABASE_MODE_2LINES
    }

}