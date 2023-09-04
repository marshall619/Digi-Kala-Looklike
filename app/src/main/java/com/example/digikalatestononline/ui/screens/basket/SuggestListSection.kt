package com.example.digikalatestononline.ui.screens.basket

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.basket.CartItem
import com.example.digikalatestononline.data.model.basket.CartStatus
import com.example.digikalatestononline.data.model.home.StoreProducts
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.ui.screens.home.MostDiscountedCard
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.h3
import com.example.digikalatestononline.ui.theme.h4
import com.example.digikalatestononline.ui.theme.searchBarBg
import com.example.digikalatestononline.viewmodel.BasketViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SuggestListSection(viewModel: BasketViewModel = hiltViewModel()) {
    viewModel.getAllProducts()

    var allProductList by remember { mutableStateOf<List<StoreProducts>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(Dispatchers.Main){
        viewModel.allProducts.collectLatest { allProductListResult->
            when (allProductListResult) {
                is NetworkResult.Success -> {
                    allProductList = allProductListResult.data ?: emptyList()
                    loading = false
                }

                is NetworkResult.Error -> {
                    Log.e("6191", "allProductListResult Api Error is :${allProductListResult.message}")
                    loading = false
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }
    }
    
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(LocalSpacing.current.small)
        .background(MaterialTheme.colors.searchBarBg))

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.medium),
        text = stringResource(id = R.string.suggestion_for_you),
        textAlign = TextAlign.Right,
        style = Typography.h4,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colors.darkText,
    )

    FlowRow(
        maxItemsInEachRow = 2,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Start
    ) {

        for (item in allProductList) {
            SuggestionItemCard(item){
                viewModel.insertCartItem(
                    CartItem(
                        it._id,
                        it.discountPercent,
                        it.image,
                        it.name,
                        it.price,
                        it.seller,
                        1,
                        CartStatus.CURRENT_CART
                    )
                )
            }
        }

    }
}