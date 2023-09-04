package com.example.digikalatestononline.data.remote

import com.example.digikalatestononline.data.model.ResponseResult
import com.example.digikalatestononline.data.model.home.AmazingItem
import com.example.digikalatestononline.data.model.home.CategoryModel
import com.example.digikalatestononline.data.model.home.Slider
import com.example.digikalatestononline.data.model.home.StoreProducts
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiInterface {

    @GET("v1/getSlider")
    suspend fun getSlider() : Response<ResponseResult<List<Slider>>>

    @GET("v1/getAmazingProducts")
    suspend fun getAmazingItem() : Response<ResponseResult<List<AmazingItem>>>

    @GET("v1/getSuperMarketAmazingProducts")
    suspend fun getSuperMarketAmazingItem() : Response<ResponseResult<List<AmazingItem>>>

    @GET("v1/get4Banners")
    suspend fun getProposalBanner() : Response<ResponseResult<List<Slider>>>

    @GET("v1/getCategories")
    suspend fun getCategories() : Response<ResponseResult<List<CategoryModel>>>

    @GET("v1/getCenterBanners")
    suspend fun getCenterBanners() : Response<ResponseResult<List<Slider>>>

    @GET("v1/getBestsellerProducts")
    suspend fun getBestsellerProducts() : Response<ResponseResult<List<StoreProducts>>>

    @GET("v1/getMostVisitedProducts")
    suspend fun getMostVisitedProducts() : Response<ResponseResult<List<StoreProducts>>>

    @GET("v1/getMostFavoriteProducts")
    suspend fun getMostFavoriteProductsItem() : Response<ResponseResult<List<StoreProducts>>>

    @GET("v1/getMostDiscountedProducts")
    suspend fun getMostDiscountedItem() : Response<ResponseResult<List<StoreProducts>>>

}