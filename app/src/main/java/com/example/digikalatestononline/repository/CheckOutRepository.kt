package com.example.digikalatestononline.repository

import com.example.digikalatestononline.data.model.checkOut.ConfirmPurchase
import com.example.digikalatestononline.data.model.checkOut.OrderDetail
import com.example.digikalatestononline.data.remote.BaseApiResponse
import com.example.digikalatestononline.data.remote.CheckOutApiInterface
import com.example.digikalatestononline.data.remote.NetworkResult
import javax.inject.Inject

class CheckOutRepository @Inject constructor(
    private val api : CheckOutApiInterface,
    ) : BaseApiResponse(){

    suspend fun getShippingCost(address : String): NetworkResult<Int> =
        safeApiCall {
            api.getShippingCost(address)
        }

    suspend fun setNewOrder(orderRequest : OrderDetail): NetworkResult<String> =
        safeApiCall {
            api.setNewOrder(orderRequest)
        }

    suspend fun confirmPurchase(confirmPurchase: ConfirmPurchase): NetworkResult<String> =
        safeApiCall {
            api.confirmPurchase(confirmPurchase)
        }


}