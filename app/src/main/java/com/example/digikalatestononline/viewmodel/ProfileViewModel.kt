package com.example.digikalatestononline.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikalatestononline.data.model.basket.CartDetails
import com.example.digikalatestononline.data.model.basket.CartItem
import com.example.digikalatestononline.data.model.basket.CartStatus
import com.example.digikalatestononline.data.model.home.StoreProducts
import com.example.digikalatestononline.data.model.profile.LoginRequest
import com.example.digikalatestononline.data.model.profile.LoginResponse
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.repository.BasketRepository
import com.example.digikalatestononline.repository.ProfileRepository
import com.example.digikalatestononline.ui.screens.basket.BasketScreenState
import com.example.digikalatestononline.ui.screens.profile.ProfileScreenState
import com.example.digikalatestononline.util.DigitHelper.applyDiscount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel(){
    var screenState by mutableStateOf(ProfileScreenState.LOGIN_STATE)
    var textFieldInput by mutableStateOf("")
    var inputPasswordState by mutableStateOf("")
    var loading by mutableStateOf(false)

    val loginResponse = MutableStateFlow<NetworkResult<LoginResponse>>(NetworkResult.Loading())

    fun login(){
        viewModelScope.launch {
            loading = true
            val loginRequest = LoginRequest(textFieldInput , inputPasswordState)
            loginResponse.emit(repository.login(loginRequest))
        }
    }


    fun refreshToken(phone : String , password : String){
        viewModelScope.launch {
            loading = true
            val loginRequest = LoginRequest(phone , password)
            loginResponse.emit(repository.login(loginRequest))
        }
    }
}