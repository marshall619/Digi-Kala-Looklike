package com.example.digikalatestononline.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.address.UserAddress
import com.example.digikalatestononline.data.model.basket.CartDetails
import com.example.digikalatestononline.ui.theme.DigikalaLightRed
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.body2
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.h4
import com.example.digikalatestononline.ui.theme.h5
import com.example.digikalatestononline.ui.theme.h6
import com.example.digikalatestononline.util.DigitHelper.digitByLocateAndSeparator

@Composable
fun CartPriceDetailSection(
    cartDetails: CartDetails,
    shippingCost: Int = 0,
) {

    var title = stringResource(id = R.string.basket_summary)
    if (shippingCost > 0) {
        title = stringResource(id = R.string.cost_details)
    }

    Column(
        modifier = Modifier.padding(
            start = LocalSpacing.current.medium,
            end = LocalSpacing.current.medium,
            top = LocalSpacing.current.medium,
            bottom = 120.dp
        )
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = title,
                style = Typography.h4,
                color = MaterialTheme.colors.darkText
            )

            Text(
                text = "${digitByLocateAndSeparator(cartDetails.totalCount.toString())} ${
                    stringResource(
                        R.string.goods
                    )
                }",
                style = Typography.h6,
                color = Color.Gray
            )

        }
        Spacer(modifier = Modifier.height(LocalSpacing.current.semiLarge))
        PriceRow(
            title = stringResource(id = R.string.goods_price),
            price = cartDetails.totalPrice.toString()
        )
        val discount =
            (1 - cartDetails.payablePrice.toDouble() / cartDetails.totalPrice.toDouble()) * 100
        PriceRow(
            title = stringResource(id = R.string.goods_discount),
            price = cartDetails.totalDiscount.toString(),
            discount = discount.toInt()
        )
        PriceRow(
            title = stringResource(id = R.string.goods_total_price),
            price = cartDetails.payablePrice.toString()
        )
        Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

        if (shippingCost > 0) {
            Divider(
                Modifier
                    .padding(
                        vertical = LocalSpacing.current.medium,
                        horizontal = LocalSpacing.current.small
                    )
                    .alpha(0.6f),
                color = Color.LightGray
            )
            PriceRow(
                title = stringResource(id = R.string.delivery_cost),
                price = shippingCost.toString()
            )
            TextWithDote(text = stringResource(R.string.shipping_cost_last_alert))
            PriceRow(
                title = stringResource(id = R.string.final_price),
                price = (cartDetails.payablePrice + shippingCost).toString()
            )
        } else {
            TextWithDote(text = stringResource(R.string.shipping_cost_alert))
        }

        Divider(
            Modifier
                .padding(
                    vertical = LocalSpacing.current.medium,
                    horizontal = LocalSpacing.current.small
                )
                .alpha(0.6f),
            color = Color.LightGray
        )
        DigiClubScore(cartDetails.payablePrice)

    }
}

@Composable
private fun TextWithDote(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.dot_bullet),
            color = Color.Gray,
            style = Typography.h4,
            modifier = Modifier.padding(LocalSpacing.current.extraSmall)
        )

        Text(
            text = text,
            style = Typography.h6,
            color = Color.Gray,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun DigiClubScore(price: Long) {

    val score = price / 100_000

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.Start) {
            Image(
                painter = painterResource(id = R.drawable.digi_score),
                contentDescription = "",
                modifier = Modifier
                    .size(22.dp)
                    .padding(LocalSpacing.current.extraSmall)
            )
            Text(
                text = stringResource(id = R.string.digiclub_score),
                style = Typography.h6,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
            )
        }

        Text(
            text = "${digitByLocateAndSeparator(score.toString())} ${stringResource(id = R.string.score)}",
            style = Typography.body2,
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colors.darkText,
        )

    }

    Spacer(modifier = Modifier.height(LocalSpacing.current.biggerSmall))
    Text(
        text = stringResource(R.string.digiclub_description),
        style = Typography.h6,
        color = Color.Gray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LocalSpacing.current.biggerSmall)
    )

}

@Composable
private fun PriceRow(
    title: String,
    price: String,
    discount: Int = 0,
) {
    var color = MaterialTheme.colors.darkText
    var ourPrice = digitByLocateAndSeparator(price)

    if (discount > 0) {
        color = MaterialTheme.colors.DigikalaLightRed
        ourPrice =
            "(${digitByLocateAndSeparator(discount.toString())}%) ${digitByLocateAndSeparator(price)}"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            style = Typography.h6,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
        )

        Row {
            Text(
                text = ourPrice,
                style = Typography.h5,
                fontWeight = FontWeight.SemiBold,
                color = color,
            )

            Icon(
                painter = painterResource(id = R.drawable.toman),
                contentDescription = "",
                tint = color,
                modifier = Modifier
                    .size(24.dp)
                    .padding(LocalSpacing.current.extraSmall)
            )
        }

    }

}