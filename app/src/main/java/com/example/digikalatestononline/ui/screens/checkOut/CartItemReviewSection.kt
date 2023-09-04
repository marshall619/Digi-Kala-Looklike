package com.example.digikalatestononline.ui.screens.checkOut

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.basket.CartDetails
import com.example.digikalatestononline.data.model.basket.CartItem
import com.example.digikalatestononline.ui.theme.DarkCyan
import com.example.digikalatestononline.ui.theme.DigikalaLightRedText
import com.example.digikalatestononline.ui.theme.LocalShape
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.extraSmall
import com.example.digikalatestononline.ui.theme.h5
import com.example.digikalatestononline.util.DigitHelper.digitByLocate

@Composable
fun CartItemReviewSection(
    cartDetail: CartDetails,
    currentCartItems: List<CartItem>,
    onDeliveryClick : () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(
                    LocalSpacing.current.medium,
                )
                .align(Alignment.Start),
            text = stringResource(id = R.string.deliveryAndTimeMethod),
            textAlign = TextAlign.Start,
            style = Typography.h5,
            fontWeight = FontWeight.Bold
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = LocalSpacing.current.small,
                ),
            shape = LocalShape.current.small,
            elevation = 2.dp,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = LocalSpacing.current.semiMedium
                    )
            ){


                Text(
                    text = digitByLocate(stringResource(id = R.string.delivery_1)),
                    textAlign = TextAlign.Start,
                    style = Typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = LocalSpacing.current.medium)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        modifier = Modifier
                            .size(16.dp),
                        tint = MaterialTheme.colors.DigikalaLightRedText,
                        painter = painterResource(id = R.drawable.k1),
                        contentDescription = ""
                    )

                    Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                    Text(
                        text = stringResource(id = R.string.fast_send),
                        textAlign = TextAlign.Start,
                        style = Typography.extraSmall,
                        color = MaterialTheme.colors.DigikalaLightRedText,
                    )

                    Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

                    Text(
                        text = digitByLocate("${cartDetail.totalCount} ${stringResource(id = R.string.goods)} "),
                        Modifier.padding(LocalSpacing.current.small),
                        style = Typography.extraSmall,
                        color = Color.Gray,
                    )

                }


                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    items(currentCartItems){item->
                        CheckoutProductCard(item)
                    }
                }

                Row() {
                    Text(
                        text = stringResource(id = R.string.ready_to_send),
                        style = Typography.extraSmall,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(vertical = LocalSpacing.current.medium),
                    )

                    Text(
                        text = " : ${stringResource(id = R.string.pishtaz_post)} (${stringResource(id = R.string.delivery_delay)})",
                        style = Typography.extraSmall,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(vertical = LocalSpacing.current.medium),
                    )
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = LocalSpacing.current.medium)
                        .clickable { onDeliveryClick() },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = stringResource(id = R.string.choose_time),
                        style = Typography.h5,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.DarkCyan,
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "",
                        tint = MaterialTheme.colors.DarkCyan,
                        modifier = Modifier
                            .padding(horizontal = LocalSpacing.current.small)
                            .size(12.dp)
                            .align(Alignment.CenterVertically)
                            .clickable {

                            }
                    )
                }

            }
        }
    }

}
