package com.excuta.searchclient.domain.di

import com.excuta.searchclient.domain.SearchUseCase
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