package com.example.digikalatestononline.ui.screens.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikalatestononline.data.model.home.CategoryModel
import com.example.digikalatestononline.data.model.home.Slider
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.ui.components.CenterBannerItem
import com.example.digikalatestononline.viewmodel.HomeViewModel

@Composable
fun CenterBannerSection(bannerId : Int , viewModel: HomeViewModel = hiltViewModel()){
    var centerBanners by remember { mutableStateOf<List<Slider>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }

    val centerBannersResult by viewModel.centerBanner.collectAsState()

    when (centerBannersResult) {
        is NetworkResult.Success -> {
            centerBanners = centerBannersResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            Log.e("6191", "centerBanners Api Error is :${centerBannersResult.message}")
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    if (centerBanners.isNotEmpty()){
        CenterBannerItem(url = centerBanners[bannerId - 1].image)
    }
}