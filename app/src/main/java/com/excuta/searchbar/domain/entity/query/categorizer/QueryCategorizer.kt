package com.excuta.searchbar.domain.entity.query.categorizer

abstract class QueryCategorizer(
    protected var next: QueryCategorizer?,
    private val defaultQuery: QueryType = QueryType.SearchQuery
) {

    protected abstract fun categorize(query: String): QueryType?

    fun getType(query: String): QueryType {
        return categorize(query) ?: (next?.getType(query) ?: defaultQuery)
    }
}

enum class QueryType { SearchQuery, LinkQuery }