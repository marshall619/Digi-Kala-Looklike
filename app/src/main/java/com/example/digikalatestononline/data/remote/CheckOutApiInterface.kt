package com.example.digikalatestononline.data.remote

import com.example.digikalatestononline.data.model.ResponseResult
import com.example.digikalatestononline.data.model.category.SubCategory
import com.example.digikalatestononline.data.model.checkOut.ConfirmPurchase
import com.example.digikalatestononline.data.model.checkOut.OrderDetail
import com.example.digikalatestononline.data.model.home.AmazingItem
import com.example.digikalatestononline.data.model.home.CategoryModel
import com.example.digikalatestononline.data.model.home.Slider
import com.example.digikalatestononline.data.model.home.StoreProducts
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CheckOutApiInterface {

    @GET("v1/getShippingCost")
    suspend fun getShippingCost(
        @Query("address") address: String
    ) : Response<ResponseResult<Int>>

    @POST("v1/setNewOrder")
    suspend fun setNewOrder(
        @Body orderRequest : OrderDetail
    ) : Response<ResponseResult<String>>

    @POST("v1/confirmPurchase")
    suspend fun confirmPurchase(
        @Body confirmPurchase: ConfirmPurchase
    ): Response<ResponseResult<String>>

}