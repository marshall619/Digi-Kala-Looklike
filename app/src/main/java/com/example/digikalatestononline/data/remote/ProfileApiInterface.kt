package com.example.digikalatestononline.data.remote

import com.example.digikalatestononline.data.model.ResponseResult
import com.example.digikalatestononline.data.model.home.AmazingItem
import com.example.digikalatestononline.data.model.home.CategoryModel
import com.example.digikalatestononline.data.model.home.Slider
import com.example.digikalatestononline.data.model.home.StoreProducts
import com.example.digikalatestononline.data.model.profile.LoginRequest
import com.example.digikalatestononline.data.model.profile.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProfileApiInterface {

    @POST("v1/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ) : Response<ResponseResult<LoginResponse>>

}