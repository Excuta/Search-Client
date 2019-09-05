package com.excuta.searchbar.presentation.di.viewmodel.factory

import com.excuta.searchbar.presentation.di.PresentationScope
import com.excuta.searchbar.presentation.di.schedulers.SchedulersModule
import com.excuta.searchbar.presentation.di.viewmodel.ViewModelsModule
import dagger.Binds
import dagger.Module

@Module(includes = [ViewModelsModule::class,SchedulersModule::class])
interface ViewModelFactoryModule {
    @Binds
    @PresentationScope
    fun bindsViewModelFactory(viewModelFactory: ViewModelFactory)
}