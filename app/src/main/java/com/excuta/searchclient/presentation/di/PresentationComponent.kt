package com.excuta.searchclient.presentation.di

import com.excuta.searchclient.domain.di.DomainComponent
import com.excuta.searchclient.presentation.di.schedulers.SchedulersModule
import com.excuta.searchclient.presentation.di.viewmodel.ViewModelsModule
import com.excuta.searchclient.presentation.di.viewmodel.factory.ViewModelFactory
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