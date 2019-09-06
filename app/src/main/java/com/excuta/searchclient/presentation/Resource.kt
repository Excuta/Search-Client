package com.excuta.searchclient.presentation

sealed class Resource<T>(
    var data: T?,
    throwable: Throwable?,
    responseCode: Int?
)

class Success<T>(data: T) :
    Resource<T>(data, null, null)

class Error<T>(throwable: Throwable, responseCode: Int?) :
    Resource<T>(null, throwable, responseCode)

class Loading<T> :
    Resource<T>(null, null, null)