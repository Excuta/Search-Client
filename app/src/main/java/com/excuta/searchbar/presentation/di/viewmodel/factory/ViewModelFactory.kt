@file:Suppress("UNCHECKED_CAST")

package com.excuta.searchbar.presentation.di.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.MapKey
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass


class ViewModelFactory
@Inject constructor(private val viewModelMap: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        viewModelMap.keys.forEach {
                if (it.isAssignableFrom(modelClass)) {
                    return viewModelMap[modelClass]?.get() as T
                }
            }
        throw IllegalArgumentException("Unknown viewmodel class")
    }

}

@Target(AnnotationTarget.FUNCTION)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)