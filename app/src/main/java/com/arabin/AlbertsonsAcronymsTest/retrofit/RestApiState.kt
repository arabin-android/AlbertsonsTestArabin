package com.arabin.AlbertsonsAcronymsTest.retrofit

data class RestAPIState<out T>(val status: RestAPIStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): RestAPIState<T> = RestAPIState(status = RestAPIStatus.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): RestAPIState<T> =
            RestAPIState(status = RestAPIStatus.ERROR, data = data, message = message)

        fun <T> loading(data: T?): RestAPIState<T> = RestAPIState(status = RestAPIStatus.LOADING, data = data, message = null)
    }
}