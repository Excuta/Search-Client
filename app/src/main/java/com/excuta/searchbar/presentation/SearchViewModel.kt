package com.excuta.searchbar.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.excuta.searchbar.domain.SearchUseCase
import com.excuta.searchbar.presentation.di.schedulers.SchedulersModule.IO_SCHEDULER
import com.excuta.searchbar.presentation.di.schedulers.SchedulersModule.MAIN_THREAD_SCHEDULER
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject
import javax.inject.Named

class SearchViewModel
@Inject constructor(
    @param:Named(MAIN_THREAD_SCHEDULER) private val mainScheduler: Scheduler,
    @param:Named(IO_SCHEDULER) private val ioScheduler: Scheduler,
    private val useCase: SearchUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _linkLiveData = MutableLiveData<Resource<String>>()
    val linkLiveData: LiveData<Resource<String>> = _linkLiveData

    val suggestions = MutableLiveData<Resource<List<String>>>()


    fun loadSuggestions(query: String) {

    }

    fun getLinkFor(query: String) {
        val disposable = useCase.getLink(query)
            .observeOn(ioScheduler)
            .subscribeOn(mainScheduler)
            .doOnSubscribe {
                Consumer<Any> {
                    _linkLiveData.postValue(Loading())
                }
            }
            .doOnError { throwable -> _linkLiveData.value = Error(throwable, null) }
            .subscribe { _linkLiveData.postValue(Success(it)) }
        compositeDisposable.add(disposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}