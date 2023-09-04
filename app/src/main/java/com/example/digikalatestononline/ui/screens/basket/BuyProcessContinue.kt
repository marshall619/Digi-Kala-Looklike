package com.example.digikalatestononline.ui.screens.basket

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
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
import com.example.digikalatestononline.ui.theme.LocalElevation
import com.example.digikalatestononline.ui.theme.LocalShape
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.body2
import com.example.digikalatestononline.ui.theme.digikalaRed
import com.example.digikalatestononline.ui.theme.h5
import com.example.digikalatestononline.ui.theme.h6
import com.example.digikalatestononline.ui.theme.semiDarkText
import com.example.digikalatestononline.util.DigitHelper

@Composable
fun BuyProcessContinue(price : Long ,shippingCost : Int = 0,onClick : () -> Unit) {

    var title = stringResource(id = R.string.goods_total_price)
    if (shippingCost > 0) {
        title = stringResource(id = R.string.final_price)
    }

    Card(
        shape = LocalShape.current.extraSmall,
        elevation = LocalElevation.current.extraSmall,
        border = BorderStroke(width = 1.dp, color = Color.LightGray.copy(0.2f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = LocalSpacing.current.medium,
                    vertical = LocalSpacing.current.semiMedium,
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.digikalaRed),
                shape = LocalShape.current.small
            ) {
                Text(
                    text = stringResource(R.string.purchase_process),
                    color = Color.White,
                    style = Typography.h5,
                    modifier = Modifier
                        .padding(
                            horizontal = LocalSpacing.current.biggerMedium,
                            vertical = LocalSpacing.current.extraSmall,
                        )
                )
            }


            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colors.semiDarkText,
                    style = Typography.h6,
                )

                Row() {
                    Text(
                        text = DigitHelper.digitByLocateAndSeparator((price  + shippingCost).toString()),
                        style = Typography.body2,
                        fontWeight = FontWeight.SemiBold
                    )

                    Image(
                        painter = painterResource(id = R.drawable.toman),
                        contentDescription = "",
                        modifier = Modifier
                            .size(LocalSpacing.current.semiLarge)
                            .padding(horizontal = LocalSpacing.current.extraSmall)
                    )
                }

            }
        }
    }

}