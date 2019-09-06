package com.excuta.searchbar.domain.entity.query.processor

import com.excuta.searchbar.domain.entity.query.categorizer.QueryType

interface QueryProcessor {
    fun getUrl(query: String): String
    interface Factory {
        fun create(queryType: QueryType): QueryProcessor
    }
}