package com.example.digikalatestononline.ui.screens.basket

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.basket.CartItem
import com.example.digikalatestononline.data.model.basket.CartStatus
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.h5
import com.example.digikalatestononline.util.Constants
import com.example.digikalatestononline.viewmodel.BasketViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NextShoppingCard(viewModel: BasketViewModel = hiltViewModel(),navController: NavHostController){

    val nextCartItemState : BasketScreenState<List<CartItem>> by viewModel.nextCartItem
        .collectAsState(BasketScreenState.Loading)

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(bottom = 60.dp)
    ) {
        item {
            if (Constants.USER_TOKEN == "null"){
                LoginOrRegisterSection(navController = navController)
            }
        }
        when(nextCartItemState){
            is BasketScreenState.Success->{
                if ((nextCartItemState as BasketScreenState.Success<List<CartItem>>).data.isEmpty()){
                    item { EmptyNextBasketShopping() }
                }else{
                    items((nextCartItemState as BasketScreenState.Success<List<CartItem>>).data){
                        CartItemCard(item = it ,mode =  CartStatus.NEXT_CART)
                    }
                }
            }
            is BasketScreenState.Loading->{
                item {
                    Column(
                        modifier = Modifier
                            .height(LocalConfiguration.current.screenHeightDp.dp - 60.dp)
                            .fillMaxWidth()
                            .padding(vertical = LocalSpacing.current.small),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.please_wait),
                            fontWeight = FontWeight.Bold,
                            style = Typography.h5,
                            color = MaterialTheme.colors.darkText,
                        )
                    }
                }

            }
            is BasketScreenState.Error->{
                Log.e("6191" , "err")
            }
        }

    }
}