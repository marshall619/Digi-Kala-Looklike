package com.example.digikalatestononline.ui.components

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.ui.screens.profile.ProfileScreenState
import com.example.digikalatestononline.util.Constants.USER_ID
import com.example.digikalatestononline.util.Constants.USER_LANGUAGE
import com.example.digikalatestononline.util.Constants.USER_PASSWORD
import com.example.digikalatestononline.util.Constants.USER_PHONE
import com.example.digikalatestononline.util.Constants.USER_TOKEN
import com.example.digikalatestononline.viewmodel.DataStoreViewModel
import com.example.digikalatestononline.viewmodel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest


@Composable
fun AppConfig(
    dataStore: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {

    getDataStoreVariables(dataStore)
    Log.e("6191","not token Refreshed is :$USER_TOKEN")
    LaunchedEffect(true){
        profileViewModel.refreshToken(USER_PHONE , USER_PASSWORD)
    }

    val loginResponse by profileViewModel.loginResponse.collectAsState()
    //LaunchedEffect(Dispatchers.Main) {
        when (loginResponse) {
            is NetworkResult.Success -> {
                loginResponse.data?.let { user ->
                    if (user.token.isNotEmpty()) {
                        dataStore.saveUserTOKEN(user.token)
                        dataStore.saveUserID(user.id)
                        dataStore.saveUserPHONE(user.phone)
                        dataStore.saveUserPASSWORD(USER_PASSWORD)

                        getDataStoreVariables(dataStore)
                        Log.e("6191","token Refreshed is :$USER_TOKEN")
                    }
                }
            }

            else -> {}
        }
    //}
}

private fun getDataStoreVariables(dataStore: DataStoreViewModel) {
    USER_LANGUAGE = dataStore.getUserLanguage()
    dataStore.saveUserLanguage(USER_LANGUAGE)

    USER_PASSWORD = dataStore.getUserPASSWORD().toString()
    USER_ID = dataStore.getUserID().toString()
    USER_PHONE = dataStore.getUserPHONE().toString()
    USER_TOKEN = dataStore.getUserTOKEN().toString()
}