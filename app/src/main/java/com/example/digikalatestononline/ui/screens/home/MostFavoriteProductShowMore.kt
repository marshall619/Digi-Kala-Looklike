package com.example.digikalatestononline.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikalatestononline.R
import com.example.digikalatestononline.ui.components.IconWithRotate
import com.example.digikalatestononline.ui.theme.DarkCyan
import com.example.digikalatestononline.ui.theme.LocalShape
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Purple80
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.h6

@Composable
fun MostFavoriteProductShowMore() {
    Column(
        modifier = Modifier
            .size(180.dp, 320.dp)
            .padding(
                end = LocalSpacing.current.medium,
                start = LocalSpacing.current.semiSmall,
                top = LocalSpacing.current.semiLarge,
                bottom = LocalSpacing.current.small
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        IconWithRotate(painterResource(id =R.drawable.show_more ), MaterialTheme.colors.DarkCyan )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.see_all),
            style = Typography.h6,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText
        )
    }
}
