package com.excuta.searchclient.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.excuta.searchclient.domain.SearchUseCase
import com.excuta.searchclient.presentation.di.schedulers.SchedulersModule.IO_SCHEDULER
import com.excuta.searchclient.presentation.di.schedulers.SchedulersModule.MAIN_THREAD_SCHEDULER
import com.excuta.searchclient.presentation.model.SearchResult
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

    private val _linkLiveData = MutableLiveData<Resource<SearchResult>>()
    val linkLiveData: LiveData<Resource<SearchResult>> = _linkLiveData

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