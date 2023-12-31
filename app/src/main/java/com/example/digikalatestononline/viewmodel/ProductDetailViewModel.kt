package com.example.digikalatestononline.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.digikalatestononline.data.model.address.UserAddress
import com.example.digikalatestononline.data.model.home.StoreProducts
import com.example.digikalatestononline.data.model.product_detail.Comment
import com.example.digikalatestononline.data.model.product_detail.NewComment
import com.example.digikalatestononline.data.model.product_detail.ProductDetail
import com.example.digikalatestononline.data.model.profile.LoginResponse
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.data.sourse.ProductCommentsDataSource
import com.example.digikalatestononline.repository.AddressRepository
import com.example.digikalatestononline.repository.ProductDetailRepository
import com.example.digikalatestononline.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val repository: ProductDetailRepository) : ViewModel(){

    val productResponse = MutableStateFlow<NetworkResult<ProductDetail>>(NetworkResult.Loading())


     fun getProductById(productId: String) {
        viewModelScope.launch {
            productResponse.emit(repository.getProductById(productId))
        }
    }

    val similarProducts = MutableStateFlow<NetworkResult<List<StoreProducts>>>(NetworkResult.Loading())


     fun getSimilarProducts(categoryId: String) {
        viewModelScope.launch {
            similarProducts.emit(repository.getSimilarProducts(categoryId))
        }
    }

    val newCommentResult = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())


     fun setNewComment(newComment: NewComment) {
        viewModelScope.launch {
            newCommentResult.emit(repository.setNewComment(newComment))
        }
    }

    var commentList : Flow<PagingData<Comment>> = flow { emit(PagingData.empty()) }

    fun getCommentList(productId : String){
        commentList = Pager(
            PagingConfig(pageSize = 10)
        ){
            ProductCommentsDataSource(repository , productId)
        }.flow.cachedIn(viewModelScope)

    }
}