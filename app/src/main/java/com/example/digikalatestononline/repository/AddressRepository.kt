package com.example.digikalatestononline.repository

import com.example.digikalatestononline.data.db.CartDao
import com.example.digikalatestononline.data.model.address.UserAddress
import com.example.digikalatestononline.data.model.basket.CartItem
import com.example.digikalatestononline.data.model.basket.CartStatus
import com.example.digikalatestononline.data.model.category.SubCategory
import com.example.digikalatestononline.data.model.home.AmazingItem
import com.example.digikalatestononline.data.model.home.CategoryModel
import com.example.digikalatestononline.data.model.home.Slider
import com.example.digikalatestononline.data.model.home.StoreProducts
import com.example.digikalatestononline.data.model.profile.LoginRequest
import com.example.digikalatestononline.data.model.profile.LoginResponse
import com.example.digikalatestononline.data.remote.AddressApiInterface
import com.example.digikalatestononline.data.remote.BaseApiResponse
import com.example.digikalatestononline.data.remote.BasketApiInterface
import com.example.digikalatestononline.data.remote.CategoryApiInterface
import com.example.digikalatestononline.data.remote.HomeApiInterface
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.data.remote.ProfileApiInterface
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

class AddressRepository @Inject constructor(
    private val api : AddressApiInterface,
    ) : BaseApiResponse(){

    suspend fun getUserAddressList(token: String): NetworkResult<List<UserAddress>> =
        safeApiCall {
            api.getUserAddress(token)
        }
}