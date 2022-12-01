package com.arabin.AlbertsonsAcronymsTest.retrofit

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestAPIBuilder {

    companion object{

        const val ACRONYM_URL = "http://www.nactem.ac.uk"


        fun build(): RestAPIService {
            val initialApiService: RestAPIService =
                buildRestApi().create(RestAPIService::class.java)
            return initialApiService
        }

        fun buildRestApi(): Retrofit{
            return Retrofit.Builder().baseUrl(ACRONYM_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(okhttpClient())
                .build()
        }


        private fun okhttpClient(
        ): OkHttpClient {
            val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true) //In case of a request failure , stop Retrofit from trying the request again automatically
                .build()
        }

    }

}