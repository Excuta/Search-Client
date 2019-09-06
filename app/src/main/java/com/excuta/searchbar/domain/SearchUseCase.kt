package com.excuta.searchbar.domain

import com.excuta.searchbar.domain.di.QueryModule.SEARCH_USECASE_CATEGORIZER
import com.excuta.searchbar.domain.entity.query.categorizer.QueryCategorizer
import com.excuta.searchbar.domain.entity.query.processor.QueryProcessor
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named


class SearchUseCase @Inject constructor(
    @param:Named(SEARCH_USECASE_CATEGORIZER) private val queryCategorizer: QueryCategorizer,
    private val queryProcessorFactory: QueryProcessor.Factory
) {

    fun getLink(query: String): Observable<String> {
        return Observable.create {
            val type = queryCategorizer.getType(query)
            val queryProcessor = queryProcessorFactory.create(type)
            it.onNext(queryProcessor.getUrl(query))
        }
    }
}