package com.excuta.searchclient.domain.entity.query.processor

import com.excuta.searchclient.domain.entity.query.categorizer.QueryType
import javax.inject.Inject

class QueryProcessorFactory
@Inject constructor() : QueryProcessor.Factory {
    override fun create(queryType: QueryType): QueryProcessor {
        return when (queryType) {
            QueryType.LinkQuery -> LinkQueryProcessor()
            QueryType.SearchQuery -> SearchQueryProcessor()
        }
    }
}