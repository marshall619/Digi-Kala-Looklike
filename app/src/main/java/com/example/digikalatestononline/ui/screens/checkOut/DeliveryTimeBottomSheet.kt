package com.example.digikalatestononline.ui.screens.checkOut

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.digikalatestononline.R
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Oranges
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.h6
import com.example.digikalatestononline.util.DigitHelper.digitByLocate

@Composable
fun DeliveryTimeBottomSheet(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = true,
                    onClick = {}
                )
                Text(
                    text = stringResource(id = R.string.pishtaz_post),
                    style = Typography.h6
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.digi_plus_icon),
                    contentDescription = "",
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = digitByLocate(stringResource(id = R.string.delivery_delay)),
                    color = MaterialTheme.colors.Oranges,
                    style = Typography.h6,
                    modifier = Modifier.padding(horizontal = LocalSpacing.current.medium),
                )
            }
        }
    }

}