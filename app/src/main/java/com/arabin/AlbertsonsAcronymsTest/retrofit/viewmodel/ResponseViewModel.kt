package com.arabin.AlbertsonsAcronymsTest.retrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.arabin.AlbertsonsAcronymsTest.retrofit.Response
import com.arabin.AlbertsonsAcronymsTest.retrofit.RestAPIBuilder
import com.arabin.AlbertsonsAcronymsTest.retrofit.RestAPIService
import com.arabin.AlbertsonsAcronymsTest.retrofit.RestAPIState
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException

class ResponseViewModel: ViewModel() {

    private var restApiHelper: RestAPIService? = null

    fun init(){
        restApiHelper = RestAPIBuilder.build()
    }

    fun getAcronym(sf: String, lf: String) = liveData(Dispatchers.Main){
        emit(RestAPIState.loading(data = null))
        try {
            val response = restApiHelper?.getAcronyms(sf, lf)
            emit(RestAPIState.success(data = response))
        }catch (exception : HttpException){
            emit(RestAPIState.error(data = null, message = exception.message ?: "Error Occurred"))
        }catch (exception : Exception){
            emit(RestAPIState.error(data = null, message = exception.message ?: "Error Occurred"))
        }
    }
}