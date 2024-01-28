package com.example.digikalatestononline.ui.screens.home


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikalatestononline.util.Constants
import com.example.digikalatestononline.util.LocaleUtils
import com.example.digikalatestononline.viewmodel.HomeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch


//hello world
@Composable
fun HomeScreen(navController: NavHostController) {
    Home(navController)
}

@Composable
fun Home(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    // FOR GETTING BACK LANGUAGE
    LocaleUtils.setLocale(LocalContext.current, Constants.USER_LANGUAGE)

    LaunchedEffect(true) {
        refreshDataFromServer(viewModel = viewModel)
    }
    SwipeRefreshSection(navController = navController, viewModel = viewModel)
}

@Composable
private fun SwipeRefreshSection(navController: NavHostController , viewModel: HomeViewModel){
    val refreshScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            refreshScope.launch {
                refreshDataFromServer(viewModel = viewModel)
            }
        }) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(bottom = 60.dp)
        ) {
            item { SearchBarSection() }
            item { TopSliderSection() }
            item { ShowCaseSection(navController) }
            item { AmazingOfferSection(navController) }
            item { ProposalBannerSection() }
            item { SuperMarketSection(navController) }
            item { CategorySection() }
            item { CenterBannerSection(1) }
            item { BestsellerProductsSection() }
            item { CenterBannerSection(2) }
            item { MostFavoriteProductsItemSection(navController) }
            item { CenterBannerSection(3) }
            item { MostVisitedProductsSection() }
            item { CenterBannerSection(4) }
            item { CenterBannerSection(5) }
            item { MostDiscountedSection() }
        }

    }
}

private suspend fun refreshDataFromServer(viewModel: HomeViewModel){
    viewModel.getAllHomeApiCall()
}