package com.excuta.searchclient.domain.di

import com.excuta.searchclient.domain.entity.query.categorizer.LinkQueryCategorizer
import com.excuta.searchclient.domain.entity.query.categorizer.QueryCategorizer
import com.excuta.searchclient.domain.entity.query.processor.QueryProcessor
import com.excuta.searchclient.domain.entity.query.processor.QueryProcessorFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object QueryModule {
    const val SEARCH_USECASE_CATEGORIZER = "SEARCH USECASE CATEGORIZER"

    @Provides
    @JvmStatic
    @Named(SEARCH_USECASE_CATEGORIZER)
    fun searchUseCaseCategorizer(): QueryCategorizer {
        return LinkQueryCategorizer()
    }

    @Provides
    @JvmStatic
    fun queryProcessorFactory(): QueryProcessor.Factory {
        return QueryProcessorFactory()
    }
}