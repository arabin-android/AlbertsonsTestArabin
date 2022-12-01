package com.arabin.AlbertsonsAcronymsTest.retrofit

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RestAPIService {

    @GET("/software/acromine/dictionary.py")
    suspend fun getAcronyms(@Query("sf")sf: String?, @Query("lf")lf: String?) : Response


}