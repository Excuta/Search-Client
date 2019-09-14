package com.excuta.searchclient.presentation.di

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val IO_SCHEDULER = "IO_SCHEDULER"
const val MAIN_THREAD_SCHEDULER = "MAIN_THREAD_SCHEDULER"

val presentationModule = module {
    single(named(IO_SCHEDULER)) { Schedulers.io() }
    single(named(MAIN_THREAD_SCHEDULER)) { AndroidSchedulers.mainThread() }
}