package com.arabin.retrofitmodule.retrofit.apirepo

import com.arabin.retrofitmodule.retrofit.Response
import com.arabin.retrofitmodule.retrofit.apiservice.RestAPIService

class RestApiRepo(private val apiSvc: RestAPIService) {

    suspend fun getAcronym(sf: String?, lf: String?): Response {
        return apiSvc.getAcronyms(sf, lf)
    }

}