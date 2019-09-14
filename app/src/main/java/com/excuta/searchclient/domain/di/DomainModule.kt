package com.excuta.searchclient.domain.di

import com.excuta.searchclient.domain.SearchUseCase
import com.excuta.searchclient.domain.entity.query.categorizer.LinkQueryCategorizer
import com.excuta.searchclient.domain.entity.query.categorizer.QueryCategorizer
import com.excuta.searchclient.domain.entity.query.processor.QueryProcessor
import com.excuta.searchclient.domain.entity.query.processor.QueryProcessorFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val SEARCH_USECASE_CATEGORIZER = "SEARCH USECASE CATEGORIZER"

val domainModule = module {
    factory<QueryCategorizer>(named(SEARCH_USECASE_CATEGORIZER)) { LinkQueryCategorizer() }
    factory<QueryProcessor.Factory> { QueryProcessorFactory() }
    factory { SearchUseCase(get(named(SEARCH_USECASE_CATEGORIZER)), get()) }
}