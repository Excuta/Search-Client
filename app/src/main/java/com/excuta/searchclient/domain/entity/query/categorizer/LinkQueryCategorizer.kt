package com.excuta.searchclient.domain.entity.query.categorizer

import com.excuta.searchclient.extensions.validUrl

class LinkQueryCategorizer
constructor(next: QueryCategorizer? = null, queryType: QueryType = QueryType.SearchQuery) :
    QueryCategorizer(next, queryType) {

    override fun categorize(query: String): QueryType? {
        return if (query.validUrl()) QueryType.LinkQuery
        else null
    }
}