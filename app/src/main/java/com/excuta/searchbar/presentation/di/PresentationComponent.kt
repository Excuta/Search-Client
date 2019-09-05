package com.excuta.searchbar.presentation.di

import com.excuta.searchbar.presentation.di.viewmodel.factory.ViewModelFactory
import com.excuta.searchbar.presentation.di.viewmodel.factory.ViewModelFactoryModule
import dagger.Component
import javax.inject.Scope

@PresentationScope
@Component(modules = [ViewModelFactoryModule::class])
interface PresentationComponent {
    fun viewModelFactory():ViewModelFactory
}

@Scope
annotation class PresentationScope