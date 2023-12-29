package com.example.digikalatestononline.repository

import com.example.digikalatestononline.data.model.profile.LoginRequest
import com.example.digikalatestononline.data.model.profile.LoginResponse
import com.example.digikalatestononline.data.remote.BaseApiResponse
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.data.remote.ProfileApiInterface
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api : ProfileApiInterface,
    ) : BaseApiResponse(){

    suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse> =
        safeApiCall {
            api.login(loginRequest)
        }
}