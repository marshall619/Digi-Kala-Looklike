package com.example.digikalatestononline.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikalatestononline.data.model.basket.CartDetails
import com.example.digikalatestononline.data.model.basket.CartItem
import com.example.digikalatestononline.data.model.basket.CartStatus
import com.example.digikalatestononline.data.model.home.StoreProducts
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.repository.BasketRepository
import com.example.digikalatestononline.ui.screens.basket.BasketScreenState
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
class BasketViewModel @Inject constructor(private val repository: BasketRepository) : ViewModel(){

    val allProducts = MutableStateFlow<NetworkResult<List<StoreProducts> >>(NetworkResult.Loading())
    val cartDetails = MutableStateFlow(CartDetails(0,0,0,0))

    val currentCartItemCount = repository.currentCartItemCount
    val nextCartItemCount = repository.nextCartItemCount

    private val _currentCartItem : MutableStateFlow<BasketScreenState<List<CartItem>>> =
        MutableStateFlow(BasketScreenState.Loading)
    val currentCartItem : StateFlow<BasketScreenState<List<CartItem>>> = _currentCartItem
    val ourCurrentItem : MutableStateFlow<List<CartItem>> = MutableStateFlow(emptyList())

    private val _nextCartItem : MutableStateFlow<BasketScreenState<List<CartItem>>> =
        MutableStateFlow(BasketScreenState.Loading)
    val nextCartItem : StateFlow<BasketScreenState<List<CartItem>>> = _nextCartItem

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                repository.currentCartItem.collectLatest { cartItems ->
                    _currentCartItem.emit(BasketScreenState.Success(cartItems))
                    ourCurrentItem.emit(cartItems)
                }
            }

            launch {
                repository.currentCartItem.collectLatest { cartItems ->
                    calculateCartDetails(cartItems)
                }
            }

            launch {
                repository.nextCartItem.collectLatest { nextCartItems ->
                    _nextCartItem.emit(BasketScreenState.Success(nextCartItems))
                }
            }
        }

    }


    private fun calculateCartDetails(items : List<CartItem>){
        var totalCount = 0
        var totalPrice = 0L
        var totalDiscount = 0L
        var payablePrice = 0L
        items.forEach { item ->
            totalPrice += item.price * item.count
            payablePrice += applyDiscount(item.price , item.discountPercent) * item.count
            totalCount += item.count
        }
        totalDiscount += totalPrice - payablePrice
        cartDetails.value = CartDetails(totalCount,totalPrice,totalDiscount,payablePrice)
    }

    fun getAllProducts(){
        viewModelScope.launch {
            launch {
                allProducts.emit(repository.getAllProducts())
            }
        }
    }

    fun insertCartItem(cart : CartItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCartItem(cart)
        }
    }

    fun removeFromCart(cart : CartItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFromCart(cart)
        }
    }

    fun changeCountCartItem(id : String , newCount : Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeCountCartItem(id , newCount)
        }
    }

    fun changeStatusCart(id : String , newStatus : CartStatus){
        viewModelScope.launch(Dispatchers.IO) {
            repository.changeStatusCart(id ,newStatus)
        }
    }

    fun deleteAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllItems()
        }
    }


}