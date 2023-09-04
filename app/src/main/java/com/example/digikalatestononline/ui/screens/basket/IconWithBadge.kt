package com.example.digikalatestononline.ui.screens.basket

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikalatestononline.ui.theme.LocalShape
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.digikalaRed
import com.example.digikalatestononline.ui.theme.extraSmall
import com.example.digikalatestononline.util.DigitHelper

@Composable
fun IconWithBadge(cartCounter: Int, icon: Painter) {
    Box(modifier = Modifier.height(28.dp)) {

        Box(
            modifier = Modifier
                .height(24.dp)
                , contentAlignment = Alignment.TopCenter
        ) {
            Icon(
                modifier = Modifier.height(24.dp).width(42.dp),
                painter = icon,
                contentDescription = null
            )
        }

        Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.BottomStart) {
            Card(shape = LocalShape.current.extraSmall, border = BorderStroke(1.dp, Color.White)) {
                Text(
                    text = DigitHelper.digitByLocateAndSeparator(cartCounter.toString()),
                    modifier = Modifier
                        .background(MaterialTheme.colors.digikalaRed)
                        .height(16.dp)
                        .padding(horizontal = LocalSpacing.current.semiSmall),
                    textAlign = TextAlign.Center,
                    style = Typography.extraSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}