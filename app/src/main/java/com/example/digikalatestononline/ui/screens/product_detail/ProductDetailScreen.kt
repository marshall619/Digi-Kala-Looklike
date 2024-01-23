package com.example.digikalatestononline.ui.screens.product_detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.product_detail.Comment
import com.example.digikalatestononline.data.model.product_detail.ProductColor
import com.example.digikalatestononline.data.model.product_detail.ProductDetail
import com.example.digikalatestononline.data.model.product_detail.SliderImage
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.ui.components.OurLoading
import com.example.digikalatestononline.ui.screens.profile.MyEditText
import com.example.digikalatestononline.viewmodel.ProductDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductDetailScreen(
    navController: NavHostController,
    productId: String,
    viewModel: ProductDetailViewModel = hiltViewModel(),
) {
    var loading by remember{ mutableStateOf(false) }
    var productDetailList by remember { mutableStateOf(ProductDetail()) }
    var sliderImageList by remember { mutableStateOf(emptyList<SliderImage>()) }
    var sliderColorList by remember { mutableStateOf(emptyList<ProductColor>()) }
    var categoryId by remember { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var commentList by remember { mutableStateOf(emptyList<Comment>()) }
    var commentCount by remember { mutableStateOf(0) }
    var technicalFeatures by remember { mutableStateOf("") }

    LaunchedEffect(Dispatchers.Main){
        viewModel.getProductById(productId)
        viewModel.productResponse.collectLatest { productDetail ->
            when (productDetail) {
                is NetworkResult.Success -> {
                    productDetailList = productDetail.data!!
                    sliderImageList = productDetailList.imageSlider ?: emptyList()
                    sliderColorList = productDetailList.colors ?: emptyList()
                    categoryId = productDetailList.categoryId ?: ""
                    description = productDetailList.description ?: ""
                    technicalFeatures = productDetailList.technicalFeatures.toString()
                    commentList = productDetailList.comments ?: emptyList()
                    commentCount = productDetailList.commentCount ?: 0
                    loading = false
                }

                is NetworkResult.Error -> {
                    Log.e("6191", "productDetail Api Error is :${productDetail.message}")
                    loading = false
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }
    }



    if (loading) {
        val config = LocalConfiguration.current
        OurLoading(config.screenHeightDp.dp, true)
    } else {
        Scaffold(
            bottomBar = {
                ProductDetailBottomBar(
                    productDetailList,
                    navController
                )
            },
            topBar = {
                ProductTopAppBar(navController = navController)
            }
        ) {
            LazyColumn(modifier = Modifier.padding(bottom = 70.dp)){
                item { ProductTopSliderSection(sliderList = sliderImageList) }
                item { ProductDetailHeaderSection(productDetailList) }
                item { ProductSelectColorSection(sliderColorList) }
                item { SellerInfoSection() }
                item { SimilarProductSection(categoryId) }
                item { ProductDescriptionSection(navController,description , technicalFeatures) }
                item { ProductCommentsSection(commentList , commentCount , productId , navController) }
                item { ProductSetCommentsSection(navController , productDetailList) }
            }
        }
    }


}