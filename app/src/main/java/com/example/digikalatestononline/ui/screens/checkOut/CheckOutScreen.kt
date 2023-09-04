package com.example.digikalatestononline.ui.screens.checkOut

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikalatestononline.data.model.checkOut.OrderDetail
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.navigation.Screen
import com.example.digikalatestononline.ui.components.OurLoading
import com.example.digikalatestononline.ui.screens.basket.BuyProcessContinue
import com.example.digikalatestononline.ui.screens.basket.CartPriceDetailSection
import com.example.digikalatestononline.util.Constants.USER_TOKEN
import com.example.digikalatestononline.viewmodel.BasketViewModel
import com.example.digikalatestononline.viewmodel.CheckOutViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CheckOutScreen(
    navController: NavHostController,
    viewModel: BasketViewModel = hiltViewModel(),
    checkOutViewModel: CheckOutViewModel = hiltViewModel(),
) {
    val cartDetails by viewModel.cartDetails.collectAsState()
    val currentItem by viewModel.ourCurrentItem.collectAsState()

    var shippingCost by remember { mutableStateOf(0) }
    var loading by remember { mutableStateOf(false) }

    var address by remember { mutableStateOf("") }
    var addressName by remember { mutableStateOf("") }
    var addressPhone by remember { mutableStateOf("") }

    val shoppingCostResult by checkOutViewModel.shoppingCost.collectAsState()

    LaunchedEffect(true){
        if (address.isNotBlank()){
            checkOutViewModel.getShippingCost(address)
        }else{
            checkOutViewModel.getShippingCost("")
        }
    }



    when (shoppingCostResult) {
        is NetworkResult.Success -> {
            shippingCost = shoppingCostResult.data ?: 0
            loading = false
        }

        is NetworkResult.Error -> {
            Log.e("6191", "shoppingCost Api Error is :${shoppingCostResult.message}")
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    var orderId by remember { mutableStateOf("") }

    LaunchedEffect(Dispatchers.Main){
        checkOutViewModel.orderResponse.collectLatest { orderResult ->
            when (orderResult) {
                is NetworkResult.Success -> {
                    orderId = orderResult.data ?: ""
                    navController.navigate(Screen.ConfirmPurchase.withArgs(orderId , cartDetails.payablePrice + shippingCost))
                    //loading = false
                }

                is NetworkResult.Error -> {
                    Log.e("6191", "orderResult Api Error is :${orderResult.message}")
                    //loading = false
                }

                is NetworkResult.Loading -> {
                    //loading = true
                }
            }
        }
    }

    val coroutinesScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded},
        skipHalfExpanded = false
    )

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetContent = {
            DeliveryTimeBottomSheet()
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn() {
                item { CheckoutTopBarSection(navController) }
                item { CartAddressSection(navController){addressList ->
                    if (addressList.isNotEmpty()){
                        address = addressList[0].address
                        addressName = addressList[0].name
                        addressPhone = addressList[0].phone
                    }
                } }
                item { CartItemReviewSection(cartDetails, currentItem){
                    coroutinesScope.launch {
                        if (modalSheetState.isVisible){
                            modalSheetState.hide()
                        }else{
                            modalSheetState.show()
                        }
                    }
                } }
                item { CartInfoSection() }
                item { CartPriceDetailSection(cartDetails, shippingCost) }

            }
            if(loading){
                OurLoading(height = 65.dp, isDark = true)
            }else{
                Row(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                ) {
                    BuyProcessContinue(cartDetails.payablePrice, shippingCost) {
                        checkOutViewModel.setNewOrder(
                            OrderDetail(
                                orderAddress = address,
                                orderProducts = currentItem,
                                orderTotalDiscount = cartDetails.totalDiscount,
                                orderTotalPrice = cartDetails.payablePrice  + shippingCost,
                                orderUserName = addressName,
                                orderUserPhone = addressPhone,
                                token = USER_TOKEN
                            )
                        )
                    }
                } 
            }
        }
    }
}