package com.excuta.searchbar.domain.entity.query.categorizer

import com.excuta.searchbar.extensions.validUrl
import javax.inject.Inject

class LinkQueryCategorizer
@Inject constructor(next: QueryCategorizer?) : QueryCategorizer(next) {
    override fun categorize(query: String): QueryType? {
        return validUrl(query)
    }

    private fun validUrl(query: String): QueryType? {
        return if (query.validUrl()) QueryType.LinkQuery
        else null
    }
}