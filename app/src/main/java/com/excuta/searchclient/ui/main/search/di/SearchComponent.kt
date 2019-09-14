package com.excuta.searchclient.ui.main.search.di

import com.excuta.searchclient.presentation.SearchViewModel
import com.excuta.searchclient.presentation.di.IO_SCHEDULER
import com.excuta.searchclient.presentation.di.MAIN_THREAD_SCHEDULER
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val SEARCH_SCOPE = "Search Scope"
val searchModule = module {
    viewModel { (string: String) -> SearchViewModel(get(named(MAIN_THREAD_SCHEDULER)),
        get(named(IO_SCHEDULER)), get(), string) }
}