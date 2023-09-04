package com.example.digikalatestononline.ui.screens.basket

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikalatestononline.R
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.digikalaRed
import com.example.digikalatestononline.ui.theme.h6
import com.example.digikalatestononline.ui.theme.selectedBottomBar
import com.example.digikalatestononline.viewmodel.BasketViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BasketScreen(navController: NavHostController) {
    Basket(navController)
}

@Composable
fun Basket(navController: NavHostController, viewModel: BasketViewModel = hiltViewModel()) {


    var selectedTabIndex by remember { mutableStateOf(0) }
    val currentCartItemCount by viewModel.currentCartItemCount.collectAsState(initial = 0)
    val nextCartItemCount by viewModel.nextCartItemCount.collectAsState(initial = 0)


    val tabTitles = listOf(
        stringResource(id = R.string.cart),
        stringResource(id = R.string.next_cart_list)
    )

    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .padding(horizontal = LocalSpacing.current.medium),
            contentColor = MaterialTheme.colors.digikalaRed,
            backgroundColor = Color.White,
            indicator = { line ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(line[selectedTabIndex])
                        .height(3.dp)
                        .background(Color.Red)
                )

            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    selectedContentColor = MaterialTheme.colors.digikalaRed,
                    unselectedContentColor = Color.Gray,
                    text = {
                        Row {
                            Text(text = title , style = Typography.h6 , fontWeight = FontWeight.SemiBold)

                            val cartCount = if (index == 0){
                                currentCartItemCount
                            }else{
                                nextCartItemCount
                            }
                            if (cartCount > 0){
                                Spacer(modifier = Modifier.width(10.dp))
                                SetBadgeToTab(
                                    selectedTabIndex = selectedTabIndex,
                                    index = index,
                                    cartCounter = cartCount
                                )
                            }
                        }
                    }
                )
            }
        }

        when(selectedTabIndex){
            0 -> ShoppingCard(navController =  navController)
            1 -> NextShoppingCard(navController = navController)
        }
    }
}


