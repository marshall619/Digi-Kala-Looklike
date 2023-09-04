package com.example.digikalatestononline.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.home.AmazingItem
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.ui.theme.DigikalaLightRed
import com.example.digikalatestononline.util.Constants
import com.example.digikalatestononline.viewmodel.HomeViewModel

@Composable
fun AmazingOfferSection(navController: NavController , viewModel: HomeViewModel = hiltViewModel()) {
    var amazingItemList by remember { mutableStateOf<List<AmazingItem>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }

    val amazingItemResult by viewModel.amazingItems.collectAsState()

    when (amazingItemResult) {
        is NetworkResult.Success -> {
            amazingItemList = amazingItemResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            Log.e("6191", "amazingItem Api Error is :${amazingItemResult.message}")
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }


    Column(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colors.DigikalaLightRed)
    ) {
        LazyRow(modifier = Modifier.background(Color.Transparent)){
            item { AmazingOfferCard( R.drawable.amazings ,  R.drawable.box) }
            items(amazingItemList){item ->
                AmazingItem(item = item , navController = navController)
            }
            item { AmazingShowMore() }
        }
    }
}




