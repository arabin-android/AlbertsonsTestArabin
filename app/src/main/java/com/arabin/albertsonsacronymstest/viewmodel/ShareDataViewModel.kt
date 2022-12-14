package com.arabin.albertsonsacronymstest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arabin.retrofitmodule.retrofit.Response

class ShareDataViewModel: ViewModel() {

    val response = MutableLiveData<Response>()

    fun setResponse(resp: Response){
        response.value = resp
    }

}