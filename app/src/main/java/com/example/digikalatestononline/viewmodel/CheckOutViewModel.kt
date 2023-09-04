package com.example.digikalatestononline.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikalatestononline.data.model.category.SubCategory
import com.example.digikalatestononline.data.model.checkOut.ConfirmPurchase
import com.example.digikalatestononline.data.model.checkOut.OrderDetail
import com.example.digikalatestononline.data.model.home.AmazingItem
import com.example.digikalatestononline.data.model.home.CategoryModel
import com.example.digikalatestononline.data.model.home.Slider
import com.example.digikalatestononline.data.model.home.StoreProducts
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.repository.CategoryRepository
import com.example.digikalatestononline.repository.CheckOutRepository
import com.example.digikalatestononline.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckOutViewModel @Inject constructor(private val repository: CheckOutRepository) : ViewModel(){

    val shoppingCost = MutableStateFlow<NetworkResult<Int>>(NetworkResult.Loading())

    fun getShippingCost(address : String){
        viewModelScope.launch {
            launch {
                shoppingCost.emit(repository.getShippingCost(address))
            }
        }
    }

    val orderResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    fun setNewOrder(orderRequest : OrderDetail){
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                orderResponse.emit(repository.setNewOrder(orderRequest))
            }
        }
    }

    val purchaseResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    fun confirmPurchase(confirmPurchase: ConfirmPurchase) {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                purchaseResponse.emit(repository.confirmPurchase(confirmPurchase))
            }
        }
    }
}