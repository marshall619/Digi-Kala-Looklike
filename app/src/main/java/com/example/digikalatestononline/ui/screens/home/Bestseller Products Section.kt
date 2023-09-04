package com.example.digikalatestononline.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.home.StoreProducts
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.ui.theme.DarkCyan
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.h3
import com.example.digikalatestononline.util.DigitHelper.digitByLocate
import com.example.digikalatestononline.viewmodel.HomeViewModel

@Composable
fun BestsellerProductsSection(viewModel: HomeViewModel = hiltViewModel()) {
    var bestsellerProducts by remember { mutableStateOf<List<StoreProducts>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }

    val bestsellerProductsResult by viewModel.bestsellerProducts.collectAsState()

    when (bestsellerProductsResult) {
        is NetworkResult.Success -> {
            bestsellerProducts = bestsellerProductsResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            Log.e("6191", "BestsellerProducts Api Error is :${bestsellerProductsResult.message}")
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.small)
    ) {
        Text(
            text = stringResource(id = R.string.best_selling_products),
            style = Typography.h3,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText
        )

        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .padding(top = LocalSpacing.current.medium)
                .height(250.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            itemsIndexed(bestsellerProducts) { index, item ->
                ProductHorizontalCard(
                    name = item.name,
                    id = digitByLocate((index + 1).toString()),
                    image = item.image
                )
            }
        }
    }
}