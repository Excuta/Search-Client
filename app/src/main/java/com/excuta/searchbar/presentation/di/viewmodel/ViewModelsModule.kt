package com.excuta.searchbar.presentation.di.viewmodel

import androidx.lifecycle.ViewModel
import com.excuta.searchbar.presentation.SearchViewModel
import com.excuta.searchbar.presentation.di.viewmodel.factory.ViewModelKey
import dagger.Binds
import dagger.multibindings.IntoMap

interface ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindsSearchViewModel(searchViewModel: SearchViewModel): ViewModel
}