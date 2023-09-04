package com.example.digikalatestononline.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikalatestononline.data.model.home.AmazingItem
import com.example.digikalatestononline.data.model.home.CategoryModel
import com.example.digikalatestononline.data.model.home.Slider
import com.example.digikalatestononline.data.model.home.StoreProducts
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel(){

    val slider = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val amazingItems = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val superMarketItem = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val banner = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val category = MutableStateFlow<NetworkResult<List<CategoryModel>>>(NetworkResult.Loading())
    val centerBanner = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val bestsellerProducts = MutableStateFlow<NetworkResult<List<StoreProducts>>>(NetworkResult.Loading())
    val mostVisitedProducts = MutableStateFlow<NetworkResult<List<StoreProducts>>>(NetworkResult.Loading())
    val mostFavoriteItem = MutableStateFlow<NetworkResult<List<StoreProducts>>>(NetworkResult.Loading())
    val mostDiscountedItem = MutableStateFlow<NetworkResult<List<StoreProducts>>>(NetworkResult.Loading())

    suspend fun getAllHomeApiCall(){
        viewModelScope.launch {
            launch {
                slider.emit(repository.getSlider())
            }
            launch {
                amazingItems.emit(repository.getAmazingItem())
            }
            launch {
                superMarketItem.emit(repository.getSuperMarketAmazingItem())
            }
            launch {
                banner.emit(repository.getProposalBanners())
            }
            launch {
                category.emit(repository.getCategories())
            }
            launch {
                centerBanner.emit(repository.getCenterBanners())
            }
            launch {
                bestsellerProducts.emit(repository.getBestsellerProducts())
            }
            launch {
                mostVisitedProducts.emit(repository.getMostVisitedProducts())
            }
            launch {
                mostFavoriteItem.emit(repository.getMostFavoriteProductsItem())
            }
            launch {
                mostDiscountedItem.emit(repository.getMostDiscountedItem())
            }
        }
    }

}