package com.example.digikalatestononline.ui.screens.product_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.digikalatestononline.R
import com.example.digikalatestononline.navigation.Screen
import com.example.digikalatestononline.ui.components.IconWithRotate
import com.example.digikalatestononline.ui.theme.DarkCyan
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.h6

@Composable
fun AllCommentShowMoreSection(
    productId : String,
    navController: NavController
){
    Column(
        modifier = Modifier
            .size(260.dp, 210.dp)
            .padding(
                end = LocalSpacing.current.medium,
                start = LocalSpacing.current.semiSmall,
                top = LocalSpacing.current.semiLarge,
                bottom = LocalSpacing.current.small
            ).
        clickable {
            navController.navigate(Screen.AllComment.withArgs(productId))
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        IconWithRotate(painterResource(id = R.drawable.show_more ), MaterialTheme.colors.DarkCyan )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.see_all),
            style = Typography.h6,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText
        )
    }
}