package com.excuta.searchbar.domain.di

import com.excuta.searchbar.domain.SearchUseCase
import dagger.Component
import javax.inject.Scope

@DomainScope
@Component(
    modules = [QueryModule::class]
)
interface DomainComponent {
    fun searchUseCase(): SearchUseCase
}

@Scope
annotation class DomainScope