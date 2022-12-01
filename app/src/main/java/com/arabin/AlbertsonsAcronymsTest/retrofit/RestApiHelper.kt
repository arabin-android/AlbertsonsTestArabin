package com.arabin.AlbertsonsAcronymsTest.retrofit

class RestApiHelper(private val apiService: RestAPIService) {

    suspend fun getAcronyms(sf: String, lf: String): Response{
        return apiService.getAcronyms(sf, lf)
    }

}