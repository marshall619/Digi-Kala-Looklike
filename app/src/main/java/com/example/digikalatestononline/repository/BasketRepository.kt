package com.example.digikalatestononline.repository

import com.example.digikalatestononline.data.db.CartDao
import com.example.digikalatestononline.data.model.basket.CartItem
import com.example.digikalatestononline.data.model.basket.CartStatus
import com.example.digikalatestononline.data.model.category.SubCategory
import com.example.digikalatestononline.data.model.home.AmazingItem
import com.example.digikalatestononline.data.model.home.CategoryModel
import com.example.digikalatestononline.data.model.home.Slider
import com.example.digikalatestononline.data.model.home.StoreProducts
import com.example.digikalatestononline.data.remote.BaseApiResponse
import com.example.digikalatestononline.data.remote.BasketApiInterface
import com.example.digikalatestononline.data.remote.CategoryApiInterface
import com.example.digikalatestononline.data.remote.HomeApiInterface
import com.example.digikalatestononline.data.remote.NetworkResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

class BasketRepository @Inject constructor(
    private val api : BasketApiInterface,
    private val     dao : CartDao
    ) : BaseApiResponse(){

    val currentCartItem = dao.getAllItems(CartStatus.CURRENT_CART)
    val nextCartItem = dao.getAllItems(CartStatus.NEXT_CART)

    val currentCartItemCount = dao.getCartItemCount(CartStatus.CURRENT_CART)
    val nextCartItemCount = dao.getCartItemCount(CartStatus.NEXT_CART)


    suspend fun getAllProducts(): NetworkResult<List<StoreProducts>> =
        safeApiCall {
            api.getAllProducts()
        }

    suspend fun insertCartItem(cart : CartItem){
        dao.insertCartItem(cart)
    }

    suspend fun removeFromCart(item : CartItem){
        dao.removeFromCart(item)
    }

    suspend fun changeCountCartItem(id : String , newCount : Int){
        dao.changeCountCartItem(id , newCount)
    }

    suspend fun changeStatusCart(id : String , newStatus : CartStatus){
        dao.changeStatusCart(id , newStatus)
    }

    suspend fun deleteAllItems() {
        dao.deleteAllItems(CartStatus.CURRENT_CART)
    }

}