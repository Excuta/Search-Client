package com.excuta.searchbar.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class SearchViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val linkLiveData = MutableLiveData<String>()
    private val suggestions = MutableLiveData<List<String>>()


    fun loadSuggestions(query: String){

    }

    fun getLinkFor(query: String){

    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}