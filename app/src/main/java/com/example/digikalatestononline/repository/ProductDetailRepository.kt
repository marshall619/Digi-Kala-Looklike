package com.example.digikalatestononline.repository

import com.example.digikalatestononline.data.model.home.StoreProducts
import com.example.digikalatestononline.data.model.product_detail.Comment
import com.example.digikalatestononline.data.model.product_detail.NewComment
import com.example.digikalatestononline.data.model.product_detail.ProductDetail
import com.example.digikalatestononline.data.remote.BaseApiResponse
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.data.remote.ProductDetailApiInterface
import retrofit2.http.Query
import javax.inject.Inject

class ProductDetailRepository @Inject constructor(
    private val api: ProductDetailApiInterface,
) : BaseApiResponse() {

    suspend fun getProductById(productId: String): NetworkResult<ProductDetail> =
        safeApiCall {
            api.getProductById(productId)
        }

    suspend fun getSimilarProducts(categoryId: String): NetworkResult<List<StoreProducts>> =
        safeApiCall {
            api.getSimilarProducts(categoryId)
        }

    suspend fun setNewComment(newComment: NewComment): NetworkResult<String> =
        safeApiCall {
            api.setNewComment(newComment)
        }

    suspend fun getAllProductComments(
        id: String,
        pageSize: String,
        pageNumber: String,
    ): NetworkResult<List<Comment>> =
        safeApiCall {
            api.getAllProductComments(id , pageSize , pageNumber)
        }
}