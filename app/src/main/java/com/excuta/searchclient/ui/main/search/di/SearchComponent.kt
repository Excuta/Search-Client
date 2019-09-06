package com.excuta.searchclient.ui.main.search.di

import com.excuta.searchclient.presentation.di.PresentationComponent
import com.excuta.searchclient.ui.main.search.SearchFragment
import dagger.Component
import javax.inject.Scope

@SearchScope
@Component(dependencies = [PresentationComponent::class])
interface SearchComponent {
    fun inject(searchFragment: SearchFragment)
}

@Scope
annotation class SearchScope