package com.arabin.AlbertsonsAcronymsTest.retrofit

interface ResetLoaderCallBacks<T> {

    fun onLoading(id: Int)
    fun onSuccess(id: Int, t: T?)
    fun onError(id: Int, t: T?, message: String?)

}