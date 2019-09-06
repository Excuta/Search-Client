package com.excuta.searchbar.domain.entity.query.processor

import com.excuta.searchbar.extensions.validUrl

class LinkQueryProcessor
internal constructor() : QueryProcessor {

    private val http = "http://"
    private val https = "https://"
    override fun getUrl(query: String): String {
        if (!query.startsWith(http) &&
            !query.startsWith(https)
        ) {
            val url = https + query
            if (url.validUrl()) return url
        }
        return query
    }
}