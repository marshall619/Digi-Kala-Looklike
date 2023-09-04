package com.example.digikalatestononline.ui.screens.product_detail

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.home.StoreProducts
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.ui.screens.home.MostFavoriteProductOffer
import com.example.digikalatestononline.ui.screens.home.MostFavoriteProductShowMore
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.h3
import com.example.digikalatestononline.viewmodel.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SimilarProductSection(
    categoryId: String,
    viewModel: ProductDetailViewModel = hiltViewModel()
){
    var similarList by remember {
        mutableStateOf<List<StoreProducts>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }
    viewModel.getSimilarProducts(categoryId)
    LaunchedEffect(true) {
        viewModel.similarProducts.collectLatest { similarListResult ->
            when (similarListResult) {
                is NetworkResult.Success -> {
                    similarList = similarListResult.data ?: emptyList()
                    loading = false
                }
                is NetworkResult.Error -> {
                    loading = false
                    Log.e("3636", "SimilarProductSection error : ${similarListResult.message}")
                }
                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }

    }


    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalSpacing.current.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.small)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LocalSpacing.current.extraSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(id = R.string.similar_product),
                style = Typography.h3,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.darkText,
            )

        }


        LazyRow {
            items(similarList) { item ->
                MostFavoriteProductOffer(item)
            }
            item {
                MostFavoriteProductShowMore()
            }
        }

    }

}