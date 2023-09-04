package com.example.digikalatestononline.repository

import com.example.digikalatestononline.data.db.CartDao
import com.example.digikalatestononline.data.model.basket.CartItem
import com.example.digikalatestononline.data.model.basket.CartStatus
import com.example.digikalatestononline.data.model.category.SubCategory
import com.example.digikalatestononline.data.model.home.AmazingItem
import com.example.digikalatestononline.data.model.home.CategoryModel
import com.example.digikalatestononline.data.model.home.Slider
import com.example.digikalatestononline.data.model.home.StoreProducts
import com.example.digikalatestononline.data.model.profile.LoginRequest
import com.example.digikalatestononline.data.model.profile.LoginResponse
import com.example.digikalatestononline.data.remote.BaseApiResponse
import com.example.digikalatestononline.data.remote.BasketApiInterface
import com.example.digikalatestononline.data.remote.CategoryApiInterface
import com.example.digikalatestononline.data.remote.HomeApiInterface
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.data.remote.ProfileApiInterface
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api : ProfileApiInterface,
    ) : BaseApiResponse(){

    suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse> =
        safeApiCall {
            api.login(loginRequest)
        }
}