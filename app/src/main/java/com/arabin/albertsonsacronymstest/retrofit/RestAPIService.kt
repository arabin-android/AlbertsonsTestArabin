package com.arabin.albertsonsacronymstest.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Arabin
 * @since 12/01/2022
 * @sample AlbertSons
 * */


interface RestAPIService {

    @GET("/software/acromine/dictionary.py")
    suspend fun getAcronyms(@Query("sf")sf: String?, @Query("lf")lf: String?) : Response


}