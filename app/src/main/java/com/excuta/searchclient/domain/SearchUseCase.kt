package com.excuta.searchclient.domain

import com.excuta.searchclient.domain.entity.query.categorizer.QueryCategorizer
import com.excuta.searchclient.domain.entity.query.processor.QueryProcessor
import com.excuta.searchclient.presentation.model.SearchResult
import io.reactivex.Observable


class SearchUseCase constructor(
    private val queryCategorizer: QueryCategorizer,
    private val queryProcessorFactory: QueryProcessor.Factory
) {

    fun getLink(query: String): Observable<SearchResult> {
        return Observable.create {
            val type = queryCategorizer.getType(query)
            val queryProcessor = queryProcessorFactory.create(type)
            val link = queryProcessor.getUrl(query)
            it.onNext(SearchResult(query, link))
        }
    }
}