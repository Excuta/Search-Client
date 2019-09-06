package com.excuta.searchbar.domain.entity.query.categorizer

import com.excuta.searchbar.extensions.validUrl

class LinkQueryCategorizer
constructor(next: QueryCategorizer? = null, queryType: QueryType = QueryType.SearchQuery) :
    QueryCategorizer(next, queryType) {

    override fun categorize(query: String): QueryType? {
        return if (query.validUrl()) QueryType.LinkQuery
        else null
    }
}