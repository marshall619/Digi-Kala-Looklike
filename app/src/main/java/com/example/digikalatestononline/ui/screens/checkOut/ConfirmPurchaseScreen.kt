package com.example.digikalatestononline.ui.screens.checkOut

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikalatestononline.util.ZarinpalPurchase
import com.example.digikalatestononline.viewmodel.BasketViewModel
import com.example.digikalatestononline.viewmodel.CheckOutViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.checkOut.ConfirmPurchase
import com.example.digikalatestononline.navigation.Screen
import com.example.digikalatestononline.ui.theme.LocalShape
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.digikalaRed
import com.example.digikalatestononline.ui.theme.h5
import com.example.digikalatestononline.util.Constants
import com.example.digikalatestononline.util.DigitHelper


@Composable
fun ConfirmPurchaseScreen(
    navController: NavHostController,
    orderId : String,
    orderPrice : String,
    basketViewModel: BasketViewModel = hiltViewModel(),
    checkoutViewModel: CheckOutViewModel = hiltViewModel()
){


    val context = LocalContext.current
    val activity = context as Activity

    var orderState by remember { mutableStateOf(context.getString(R.string.waiting_for_purchase)) }

    LaunchedEffect(true ){
        ZarinpalPurchase.fakePurchase(
            activity,
            orderPrice.toLong(),
            "خرید تستی از دیجی کالا"
        ) { transactionID ->
            orderState = context.getString(R.string.purchase_is_ok)
            basketViewModel.deleteAllItems()
            checkoutViewModel.confirmPurchase(
                ConfirmPurchase(
                    token = Constants.USER_TOKEN,
                    transactionId = transactionID,
                    orderId = orderId
                )
            )
            Log.e("3636", "Transaction ID : $transactionID")
        }
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                LocalSpacing.current.medium,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.final_price),
                style = Typography.h5
            )

            Text(
                text = DigitHelper.digitByLocateAndSeparator(orderPrice),
                style = Typography.h5
            )
        }

        Spacer(modifier = Modifier.height(LocalSpacing.current.small))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.order_status),
                style = Typography.h5
            )

            Text(
                text = orderState,
                style = Typography.h5
            )
        }

        Spacer(modifier = Modifier.height(LocalSpacing.current.small))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.order_code),
                style = Typography.h5
            )

            Text(
                text = orderId,
                style = Typography.h5
            )
        }

        Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

        Button(
            onClick = {
                navController.navigate(Screen.Home.route){
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            },
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.digikalaRed),
            shape = LocalShape.current.small,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        LocalSpacing.current.small,
                    ),
                text = stringResource(id = R.string.return_to_home_page),
                color = MaterialTheme.colors.digikalaRed,
                style = Typography.h5,
                fontWeight = FontWeight.Bold,
            )
        }

    }


}