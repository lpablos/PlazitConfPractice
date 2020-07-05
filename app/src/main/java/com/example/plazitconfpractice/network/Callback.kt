package com.example.plazitconfpractice.network

import java.lang.Exception

interface Callback<T> {

    fun onSucces(result:T?)

    fun onFail(exception: Exception)

}