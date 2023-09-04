package com.example.digikalatestononline.ui.screens.checkOut

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.address.UserAddress
import com.example.digikalatestononline.data.model.home.AmazingItem
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.h3
import com.example.digikalatestononline.ui.theme.searchBarBg
import com.example.digikalatestononline.viewmodel.AddressViewModel

@Composable
fun CheckoutTopBarSection(navController: NavHostController , viewModel : AddressViewModel = hiltViewModel()){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = LocalSpacing.current.medium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = LocalSpacing.current.medium)
                .clickable {
                    navController.popBackStack()
                }
                .size(24.dp)

        )

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.address_and_time),
            textAlign = TextAlign.Start,
            style = Typography.h3,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.darkText,
        )

    }

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(3.dp)
            .background(MaterialTheme.colors.searchBarBg)
    )
}