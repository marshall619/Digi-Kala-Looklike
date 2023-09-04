package com.example.digikalatestononline.ui.screens.checkOut

import com.example.digikalatestononline.data.model.basket.CartItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikalatestononline.ui.theme.LocalShape
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.extraSmall
import com.example.digikalatestononline.util.DigitHelper


@Composable
fun CheckoutProductCard(item : CartItem){
    Box(
        modifier = Modifier
            .padding(LocalSpacing.current.small)
            .size(75.dp)
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(item.image),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
        }

        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(LocalShape.current.extraSmall),
            contentAlignment = Alignment.BottomEnd
        ){
            Text(
                text = DigitHelper.digitByLocate(item.count.toString()),
                style = Typography.extraSmall
            )
        }

    }

    Divider(
        color = Color.LightGray,
        modifier = Modifier
            .height(70.dp)
            .alpha(0.4f)
            .width(1.dp)

    )

}