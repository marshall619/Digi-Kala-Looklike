package com.example.digikalatestononline.ui.screens.category

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikalatestononline.ui.screens.home.SearchBarSection
import com.example.digikalatestononline.util.Constants
import com.example.digikalatestononline.util.LocaleUtils
import com.example.digikalatestononline.viewmodel.CategoryViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun CategoryScreen(navController: NavHostController) {
    Category(navController = navController)
}

@Composable
fun Category(navController: NavHostController , viewModel : CategoryViewModel = hiltViewModel()){
    // FOR GETTING BACK LANGUAGE
    LocaleUtils.setLocale(LocalContext.current, Constants.USER_LANGUAGE)

    LaunchedEffect(true) {
        refreshDataFromServer(viewModel = viewModel)
    }
    SwipeRefreshSection(navController = navController, viewModel = viewModel)
}

@Composable
private fun SwipeRefreshSection(navController: NavHostController , viewModel: CategoryViewModel){
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
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
        ) {
            item { SearchBarSection() }
            item { SubCategorySection() }
        }

    }
}

private suspend fun refreshDataFromServer(viewModel: CategoryViewModel){
    viewModel.getAllApiCall()
}