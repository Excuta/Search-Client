package com.excuta.searchbar.presentation.di

import com.excuta.searchbar.domain.di.DomainComponent
import com.excuta.searchbar.presentation.di.schedulers.SchedulersModule
import com.excuta.searchbar.presentation.di.viewmodel.ViewModelsModule
import com.excuta.searchbar.presentation.di.viewmodel.factory.ViewModelFactory
import dagger.Component
import javax.inject.Scope

@PresentationScope
@Component(
    dependencies = [DomainComponent::class],
    modules = [ViewModelsModule::class, SchedulersModule::class]
)
interface PresentationComponent {
    fun viewModelFactory(): ViewModelFactory
}

@Scope
annotation class PresentationScope