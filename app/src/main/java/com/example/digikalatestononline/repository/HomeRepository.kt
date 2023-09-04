package com.example.digikalatestononline.repository

import com.example.digikalatestononline.data.model.home.AmazingItem
import com.example.digikalatestononline.data.model.home.CategoryModel
import com.example.digikalatestononline.data.model.home.Slider
import com.example.digikalatestononline.data.model.home.StoreProducts
import com.example.digikalatestononline.data.remote.BaseApiResponse
import com.example.digikalatestononline.data.remote.HomeApiInterface
import com.example.digikalatestononline.data.remote.NetworkResult
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api : HomeApiInterface) : BaseApiResponse(){

    suspend fun getSlider(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getSlider()
        }
    suspend fun getAmazingItem(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getAmazingItem()
        }
    suspend fun getSuperMarketAmazingItem(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getSuperMarketAmazingItem()
        }

    suspend fun getProposalBanners(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getProposalBanner()
        }

    suspend fun getCategories(): NetworkResult<List<CategoryModel>> =
        safeApiCall {
            api.getCategories()
        }

    suspend fun getCenterBanners(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getCenterBanners()
        }

    suspend fun getBestsellerProducts(): NetworkResult<List<StoreProducts>> =
        safeApiCall {
            api.getBestsellerProducts()
        }

    suspend fun getMostVisitedProducts(): NetworkResult<List<StoreProducts>> =
        safeApiCall {
            api.getMostVisitedProducts()
        }

    suspend fun getMostFavoriteProductsItem(): NetworkResult<List<StoreProducts>> =
        safeApiCall {
            api.getMostFavoriteProductsItem()
        }

    suspend fun getMostDiscountedItem(): NetworkResult<List<StoreProducts>> =
        safeApiCall {
            api.getMostDiscountedItem()
        }

}