package com.arabin.albertsonsacronymstest.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabin.retrofitmodule.retrofit.Response
import com.arabin.retrofitmodule.retrofit.RestAPIState
import com.arabin.retrofitmodule.retrofit.apirepo.RestApiRepo
import com.arabin.retrofitmodule.retrofit.repomgrimpl.RepoMgrImpl
import com.arabin.retrofitmodule.retrofit.repomgrintfc.RepoManagerInterface
import com.arabin.roomdb.DbManagerImpl
import com.arabin.roomdb.DbManagerInterface
import com.arabin.roomdb.DbManagerRepo
import com.arabin.roomdb.entity.Macronym
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Arabin
 * @since 12/01/2022
 * @sample AlbertSons
 * */


class ResponseViewModel : ViewModel() {

    var macroShort: String? = null
    var macroFull: String? = null
    private var apiRepo: RestApiRepo? = null
    private var dbManager: DbManagerRepo? = null
    private var _data: MutableLiveData<RestAPIState<Response?>?>? = null
    val data: LiveData<RestAPIState<Response?>?>?
        get() = _data
    private var _emptyString: MutableLiveData<String?>? = null
    private var macroList: MutableLiveData<RestAPIState<List<Macronym?>?>>? = null
    val liveMacroList: LiveData<RestAPIState<List<Macronym?>?>>?
        get() = macroList
    val emptyString : LiveData<String?>?
        get() = _emptyString

    fun init(context: Context){
        apiRepo = RepoMgrImpl().getRestApiRepo()
        dbManager = DbManagerImpl().getRoomDbRepo(context)
        _emptyString = MutableLiveData(null)
        _data = MutableLiveData(RestAPIState.idleState(data = null))
    }

    fun getAcronym(sf: String?, lf: String?) = viewModelScope.launch {Dispatchers.IO
        try {
            val data = apiRepo?.getAcronym(sf, lf)
            _data?.postValue(RestAPIState.success(data = data))

            val listItems = mutableListOf<Macronym>()
            data?.forEach{
                listItems.add(Macronym(0,it.sf, "Dummy"))
            }

/*            dbManager?.insertAllMacros(listItems)

            macroList?.postValue(RestAPIState.success(data = dbManager?.getAllMacros()))*/

        }catch (exception: Exception) {
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
        super.onCleared()
    }
}