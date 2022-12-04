package com.arabin.albertsonsacronymstest.retrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabin.albertsonsacronymstest.retrofit.Response
import com.arabin.albertsonsacronymstest.retrofit.RestAPIBuilder
import com.arabin.albertsonsacronymstest.retrofit.RestAPIService
import com.arabin.albertsonsacronymstest.retrofit.RestAPIState
import kotlinx.coroutines.launch
import retrofit2.HttpException

/**
 * @author Arabin
 * @since 12/01/2022
 * @sample AlbertSons
 * */


class ResponseViewModel : ViewModel() {

    private var restApiHelper: RestAPIService? = null
    var macroShort: String? = null
    var macroFull: String? = null
    private var _data: MutableLiveData<RestAPIState<Response?>?>? = null
    val data: LiveData<RestAPIState<Response?>?>?
        get() = _data
    private var _emptyString: MutableLiveData<String?>? = null
    val emptyString : LiveData<String?>?
        get() = _emptyString

    init {
        restApiHelper = RestAPIBuilder.getInstance()
        _emptyString = MutableLiveData(null)
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
            _emptyString?.postValue("Enter a macro or full-form")
        }
    }

    fun clearViewModel() {
        _emptyString?.postValue(null)
        _data?.postValue(null)
    }
}