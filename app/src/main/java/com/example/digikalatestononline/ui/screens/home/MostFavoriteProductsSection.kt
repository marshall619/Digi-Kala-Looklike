package com.example.digikalatestononline.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.home.Slider
import com.example.digikalatestononline.data.model.home.StoreProducts
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.navigation.Screen
import com.example.digikalatestononline.ui.theme.DarkCyan
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.h3
import com.example.digikalatestononline.ui.theme.h6
import com.example.digikalatestononline.viewmodel.HomeViewModel

@Composable
fun MostFavoriteProductsItemSection(navController: NavController,viewModel: HomeViewModel = hiltViewModel()) {
    var mostFavoriteItems by remember { mutableStateOf<List<StoreProducts>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }

    val mostFavoriteItemsResult by viewModel.mostFavoriteItem.collectAsState()

    when (mostFavoriteItemsResult) {
        is NetworkResult.Success -> {
            mostFavoriteItems = mostFavoriteItemsResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            Log.e("6191", "centerBanners Api Error is :${mostFavoriteItemsResult.message}")
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.small)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LocalSpacing.current.small),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.favorite_product),
                style = Typography.h3,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.darkText
            )

            Text(
                text = stringResource(id = R.string.see_all),
                style = Typography.h6,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.DarkCyan
            )
        }

        LazyRow(){
            items(mostFavoriteItems){item ->
                MostFavoriteProductOffer(item){
                    navController.navigate(Screen.ProductDetail.withArgs(item._id))
                }
            }
            item{
                MostFavoriteProductShowMore()
            }
        }
    }
}