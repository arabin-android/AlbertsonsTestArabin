package com.arabin.retrofitmodule.retrofit.repomgrimpl

import com.arabin.retrofitmodule.retrofit.RestApiHelper.RestAPIBuilder
import com.arabin.retrofitmodule.retrofit.apirepo.RestApiRepo
import com.arabin.retrofitmodule.retrofit.apiservice.RestAPIService
import com.arabin.retrofitmodule.retrofit.repomgrintfc.RepoManagerInterface

open class RepoMgrImpl: RepoManagerInterface {
    private var restApiRepo: RestApiRepo? = null
    private var apiService: RestAPIService? = null

    override fun getRestApiRepo(): RestApiRepo? {
        if (apiService == null)
            apiService = RestAPIBuilder.getInstance()
        if (restApiRepo == null)
            restApiRepo = apiService?.let { RestApiRepo(it) }
        return restApiRepo
    }
}