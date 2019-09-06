package com.excuta.searchclient.domain.entity.query.processor

class LinkQueryProcessor
internal constructor() : QueryProcessor {

    private val http = "http://"
    private val https = "https://"
    override fun getUrl(query: String): String {
        if (!query.startsWith(http) &&
            !query.startsWith(https)
        ) {
            return https + query
        }
        return query
    }
}