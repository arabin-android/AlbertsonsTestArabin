package com.arabin.retrofitmodule.retrofit.apiservice

import com.arabin.retrofitmodule.retrofit.Response
import com.arabin.retrofitmodule.retrofit.RestApiEndPointInterface
import com.arabin.retrofitmodule.retrofit.RestApiEndPointInterface.GET_ACRONYM
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Arabin
 * @since 12/01/2022
 * @sample AlbertSons
 * */


interface RestAPIService: RestApiEndPointInterface {

    @GET(GET_ACRONYM)
    suspend fun getAcronyms(@Query("sf")sf: String?, @Query("lf")lf: String?) : Response

}