package com.arabin.albertsonsacronymstest.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabin.albertsonsacronymstest.retrofit.Response
import com.arabin.albertsonsacronymstest.retrofit.RestAPIBuilder
import com.arabin.albertsonsacronymstest.retrofit.RestAPIService
import com.arabin.albertsonsacronymstest.retrofit.RestAPIState
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ResponseViewModel : ViewModel() {

    private var restApiHelper: RestAPIService? = null
    var macroShort: String? = null
    var macroFull: String? = null
    var _data: MutableLiveData<RestAPIState<Response?>?>? = null
    var emptyString: MutableLiveData<String?>? = null

    init {
        restApiHelper = RestAPIBuilder.build()
        emptyString = MutableLiveData(null)
        _data = MutableLiveData(RestAPIState.idleState(data = null))
    }

    fun getAcronym(sf: String?, lf: String?) = viewModelScope.launch {
        try {
            _data?.postValue(RestAPIState.success(data = restApiHelper?.getAcronyms(sf, lf)))
        } catch (exception: HttpException) {
            _data?.postValue(
                exception.message?.let {
                    RestAPIState.error(
                        data = null, it
                    )
                }
            )
        } catch (exception: Exception) {
            _data?.postValue(
                exception.message?.let {
                    RestAPIState.error(
                        data = null, it
                    )
                }
            )
        }
    }


    fun onSubmitButtonClicked() {
        if (!macroShort.isNullOrBlank() || !macroFull.isNullOrBlank()) {
            _data?.postValue(RestAPIState.loading(data = null, "Loading please wait..."))
            getAcronym(macroShort, macroFull)
        } else {
            emptyString?.postValue("Enter a macro or full-form")
        }
    }

    fun clearViewModel() {
        emptyString?.postValue(null)
        _data?.postValue(null)
    }
}