package com.excuta.searchclient.domain.entity.query.processor

import com.excuta.searchclient.domain.entity.query.categorizer.QueryType

interface QueryProcessor {
    fun getUrl(query: String): String
    interface Factory {
        fun create(queryType: QueryType): QueryProcessor
    }
}