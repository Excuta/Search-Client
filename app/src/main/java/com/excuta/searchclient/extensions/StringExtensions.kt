package com.excuta.searchclient.extensions

import java.util.regex.Pattern


fun String.validUrl(): Boolean {
    val regex = Pattern.compile(
        "\\b(?:(https?|ftp|file)://|www\\.)?[-A-Z0-9+&#/%?=~_|$!:,.;]*[A-Z0-9+&@#/%=~_|$]\\.[-A-Z0-9+&@#/%?=~_|$!:,.;]*[A-Z0-9+&@#/%=~_|$]",
        Pattern.CASE_INSENSITIVE or Pattern.UNICODE_CASE
    )
    val regexMatcher = regex.matcher(this)
    return regexMatcher.matches()
}