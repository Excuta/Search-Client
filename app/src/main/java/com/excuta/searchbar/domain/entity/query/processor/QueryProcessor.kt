package com.excuta.searchbar.domain.entity.query.processor

interface QueryProcessor {
    fun getUrl(query: String): String
}