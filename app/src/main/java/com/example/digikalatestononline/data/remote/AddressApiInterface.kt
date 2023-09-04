package com.example.digikalatestononline.data.remote

import com.example.digikalatestononline.data.model.ResponseResult
import com.example.digikalatestononline.data.model.address.UserAddress
import com.example.digikalatestononline.data.model.profile.LoginRequest
import com.example.digikalatestononline.data.model.profile.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AddressApiInterface {

    @GET("v1/getUserAddress")
    suspend fun getUserAddress(
        @Query("token") token: String
    ) : Response<ResponseResult<List<UserAddress>>>

}