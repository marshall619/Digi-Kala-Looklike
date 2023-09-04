package com.example.digikalatestononline.ui.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.digikalatestononline.data.model.home.Slider
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.ui.theme.LocalShape
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.viewmodel.HomeViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProposalBannerSection(viewModel: HomeViewModel = hiltViewModel()) {

    var banners by remember { mutableStateOf<List<Slider>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }

    val bannerItemResult by viewModel.banner.collectAsState()

    when (bannerItemResult) {
        is NetworkResult.Success -> {
            banners = bannerItemResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            Log.e("6191", "bannerItem Api Error is :${bannerItemResult.message}")
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    //lazyVerticalGrade
    FlowRow(
        maxItemsInEachRow = 2, modifier = Modifier
            .fillMaxWidth()
            .height(290.dp)
            .padding(
                LocalSpacing.current.small
            )
    ) {
        for (item in banners) {
            ProposalCardItem(item)
        }
    }

}

@Composable
fun ProposalCardItem(imageUrl: Slider) {
    Card(
        shape = LocalShape.current.semiMedium,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(140.dp)
            .padding(
                LocalSpacing.current.small
            )
    ) {

        Image(
            painter = rememberAsyncImagePainter(model = imageUrl.image),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

    }
}