package com.example.digikalatestononline.ui.screens.checkOut

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.address.UserAddress
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.ui.components.OurLoading
import com.example.digikalatestononline.ui.theme.LightCyan
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.body2
import com.example.digikalatestononline.ui.theme.extraSmall
import com.example.digikalatestononline.ui.theme.h4
import com.example.digikalatestononline.ui.theme.h5
import com.example.digikalatestononline.viewmodel.AddressViewModel

@Composable
fun CartAddressSection(
    navController: NavHostController,
    viewModel: AddressViewModel = hiltViewModel(),
    onAddressCall: (List<UserAddress>) -> Unit,
) {
    var address = stringResource(id = R.string.no_address)
    var addressBtnText = stringResource(id = R.string.add_address)
    var addressName = ""

    var userAddressList by remember { mutableStateOf<List<UserAddress>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }

    val userAddressResult by viewModel.userAddressList.collectAsState()

    when (userAddressResult) {
        is NetworkResult.Success -> {
            userAddressList = userAddressResult.data ?: emptyList()
            if (userAddressList.isNotEmpty()){
                onAddressCall(userAddressList)
                address = userAddressList[0].address
                addressBtnText = stringResource(id = R.string.change_address)
                addressName = userAddressList[0].name
            }
            loading = false
        }

        is NetworkResult.Error -> {
            Log.e("6191", "userAddress Api Error is :${userAddressResult.message}")
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    
    if (loading){
        OurLoading(height = 150.dp, isDark = true)
    }else{
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {

            Image(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "",
                modifier = Modifier
                    .size(22.dp)
                    .weight(0.15f)
                    .align(Alignment.CenterVertically),
            )

            Column(
                modifier = Modifier
                    .weight(0.85f)
                    .padding(vertical = LocalSpacing.current.medium),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ){

                Text(
                    text = stringResource(id = R.string.send_to),
                    textAlign = TextAlign.Start,
                    style = Typography.body2,
                    color = Color.Gray,
                )

                Text(
                    text = address,
                    textAlign = TextAlign.Start,
                    style = Typography.h5,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 3
                )

                Text(
                    text = addressName,
                    textAlign = TextAlign.Start,
                    style = Typography.h4,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )

            }


        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalSpacing.current.medium),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ){

            Text(
                modifier = Modifier
                    .padding(horizontal = LocalSpacing.current.extraSmall)
                    .clickable {

                    },
                text = addressBtnText,
                textAlign = TextAlign.End,
                style = Typography.extraSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.LightCyan,
            )

            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "",
                tint = MaterialTheme.colors.LightCyan,
                modifier = Modifier
                    .size(12.dp)
                    .align(Alignment.CenterVertically)
            )

        }
    }

    Divider(
        modifier = Modifier
            .padding(vertical = LocalSpacing.current.medium)
            .fillMaxWidth()
            .height(LocalSpacing.current.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )

}