package com.example.digikalatestononline.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikalatestononline.data.datastore.DataStoreRepository
import com.example.digikalatestononline.util.Constants.PERSIAN_LANG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {


    companion object {
        const val USER_LANGUAGE_KEY = "USER_LANGUAGE_KEY"
        const val USER_TOKEN_KEY = "USER_TOKEN_KEY"
        const val USER_ID_KEY = "USER_ID_KEY"
        const val USER_PASSWORD_KEY = "USER_PHONE_KEY"
        const val USER_PHONE_KEY = "USER_PASSWORD_KEY"
    }

    fun saveUserLanguage(value: String) {
        viewModelScope.launch {
            repository.putString(USER_LANGUAGE_KEY, value)
        }
    }

    fun getUserLanguage(): String = runBlocking {
        repository.getString(USER_LANGUAGE_KEY) ?: PERSIAN_LANG
    }

    fun saveUserTOKEN(value: String) {
        viewModelScope.launch {
            repository.putString(USER_TOKEN_KEY, value)
        }
    }

    fun getUserTOKEN(): String? = runBlocking {
        repository.getString(USER_TOKEN_KEY)
    }

    fun saveUserID(value: String) {
        viewModelScope.launch {
            repository.putString(USER_ID_KEY, value)
        }
    }

    fun getUserID(): String? = runBlocking {
        repository.getString(USER_ID_KEY)
    }

    fun saveUserPASSWORD(value: String) {
        viewModelScope.launch {
            repository.putString(USER_PASSWORD_KEY, value)
        }
    }

    fun getUserPASSWORD(): String? = runBlocking {
        repository.getString(USER_PASSWORD_KEY)
    }

    fun saveUserPHONE(value: String) {
        viewModelScope.launch {
            repository.putString(USER_PHONE_KEY, value)
        }
    }

    fun getUserPHONE(): String ? = runBlocking {
        repository.getString(USER_PHONE_KEY)
    }


}