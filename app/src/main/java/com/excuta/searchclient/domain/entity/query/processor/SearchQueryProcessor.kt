package com.excuta.searchclient.domain.entity.query.processor

class SearchQueryProcessor
internal constructor() : QueryProcessor {

    private var baseUrl = "https://www.google.com/search?q="
    override fun getUrl(query: String): String {
        val stringBuilder = StringBuilder(baseUrl)
        val list = query.split(" ")
        list.forEachIndexed { index, string ->
            stringBuilder.append(string)
            if (index != list.lastIndex) stringBuilder.append("+")
        }
        return stringBuilder.toString()
    }
}