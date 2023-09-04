package com.example.digikalatestononline.repository

import com.example.digikalatestononline.data.model.category.SubCategory
import com.example.digikalatestononline.data.model.home.AmazingItem
import com.example.digikalatestononline.data.model.home.CategoryModel
import com.example.digikalatestononline.data.model.home.Slider
import com.example.digikalatestononline.data.model.home.StoreProducts
import com.example.digikalatestononline.data.remote.BaseApiResponse
import com.example.digikalatestononline.data.remote.CategoryApiInterface
import com.example.digikalatestononline.data.remote.HomeApiInterface
import com.example.digikalatestononline.data.remote.NetworkResult
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val api : CategoryApiInterface) : BaseApiResponse(){

    suspend fun getSubCategories(): NetworkResult<SubCategory> =
        safeApiCall {
            api.getSubCategories()
        }

}