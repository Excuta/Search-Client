package com.excuta.searchbar.extensions

import java.lang.Exception
import java.net.URL

fun String.validUrl():Boolean{
    return try{
        URL(this).toURI()
        true
    }catch (ex:Exception){
        false
    }
}