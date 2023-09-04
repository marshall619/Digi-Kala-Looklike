package com.example.digikalatestononline.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.basket.CartItem
import com.example.digikalatestononline.data.model.basket.CartStatus
import com.example.digikalatestononline.ui.theme.DarkCyan
import com.example.digikalatestononline.ui.theme.DigikalaDarkRed
import com.example.digikalatestononline.ui.theme.DigikalaLightGreen
import com.example.digikalatestononline.ui.theme.DigikalaLightRed
import com.example.digikalatestononline.ui.theme.LocalShape
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.body2
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.digikalaRed
import com.example.digikalatestononline.ui.theme.extraSmall
import com.example.digikalatestononline.ui.theme.h3
import com.example.digikalatestononline.ui.theme.h4
import com.example.digikalatestononline.ui.theme.h6
import com.example.digikalatestononline.ui.theme.semiDarkText
import com.example.digikalatestononline.ui.theme.veryExtraSmall
import com.example.digikalatestononline.util.DigitHelper.digitByLocateAndSeparator
import com.example.digikalatestononline.viewmodel.BasketViewModel

@Composable
fun CartItemCard(item: CartItem, viewModel: BasketViewModel = hiltViewModel(), mode: CartStatus) {

    val count = remember {
        mutableStateOf(item.count)
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = LocalSpacing.current.extraSmall)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalSpacing.current.medium)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.your_shopping_list),
                        style = Typography.h4,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.darkText
                    )
                    Text(
                        text = "${digitByLocateAndSeparator(count.value.toString())}  کالا",
                        style = Typography.h6,
                        color = Color.Gray
                    )

                }
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More Options",
                    tint = MaterialTheme.colors.darkText
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(item.image),
                    contentDescription = "cart item Photo",
                    modifier = Modifier
                        .width(130.dp)
                        .height(90.dp)
                        .weight(.3f),
                )

                Column(modifier = Modifier.weight(.7f)) {
                    Text(
                        text = item.name,
                        style = Typography.h6,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.darkText,
                        maxLines = 2,
                        modifier = Modifier
                            .padding(vertical = LocalSpacing.current.extraSmall)
                    )

                    DetailRow(
                        painterResource(id = R.drawable.warranty),
                        stringResource(id = R.string.warranty),
                        color = MaterialTheme.colors.darkText,
                        fontStyle = Typography.extraSmall
                    )

                    DetailRow(
                        painterResource(id = R.drawable.store),
                        stringResource(id = R.string.digikala),
                        color = MaterialTheme.colors.darkText,
                        fontStyle = Typography.extraSmall
                    )

                    Row {
                        Column(
                            modifier = Modifier
                                .width(16.dp)
                                .fillMaxHeight()
                                .padding(vertical = LocalSpacing.current.extraSmall),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.in_stock),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(16.dp),
                                tint = MaterialTheme.colors.DarkCyan
                            )

                            Divider(
                                Modifier
                                    .width(2.dp)
                                    .height(16.dp)
                                    .alpha(0.6f),
                                color = Color.LightGray
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.circle),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(10.dp)
                                    .padding(1.dp),
                                tint = MaterialTheme.colors.DarkCyan,
                            )

                            Divider(
                                Modifier
                                    .width(2.dp)
                                    .height(16.dp)
                                    .alpha(0.6f),
                                color = Color.LightGray
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.circle),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(10.dp)
                                    .padding(1.dp),
                                tint = MaterialTheme.colors.DarkCyan,
                            )
                        }
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            Text(
                                text = item.seller,
                                style = Typography.extraSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colors.semiDarkText,
                                modifier = Modifier
                                    .padding(vertical = LocalSpacing.current.extraSmall),
                            )

                            DetailRow(
                                painterResource(id = R.drawable.k1),
                                stringResource(id = R.string.digikala_send),
                                color = MaterialTheme.colors.DigikalaLightRed,
                                fontStyle = Typography.veryExtraSmall
                            )

                            DetailRow(
                                painterResource(id = R.drawable.k2),
                                stringResource(id = R.string.fast_send),
                                color = MaterialTheme.colors.DigikalaLightGreen,
                                fontStyle = Typography.veryExtraSmall
                            )

                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(LocalSpacing.current.semiLarge))


            Row(
                modifier = Modifier.align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier
                        .clip(LocalShape.current.semiSmall)
                        .border(
                            1.dp,
                            Color.LightGray.copy(0.6f),
                            LocalShape.current.semiSmall
                        )
                ) {


                    if (mode == CartStatus.CURRENT_CART) {
                        Row(
                            modifier = Modifier
                                .padding(
                                    horizontal = LocalSpacing.current.small,
                                    vertical = LocalSpacing.current.extraSmall
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painterResource(id = R.drawable.ic_increase_24),
                                contentDescription = "increase icon",
                                tint = MaterialTheme.colors.digikalaRed,
                                modifier = Modifier.clickable {
                                    count.value++
                                    viewModel.changeCountCartItem(item.id, count.value)
                                }
                            )

                            Text(
                                text = digitByLocateAndSeparator(count.value.toString()),
                                style = Typography.body2,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colors.digikalaRed,
                                modifier = Modifier
                                    .padding(horizontal = LocalSpacing.current.medium)
                            )


                            if (count.value == 1) {
                                Icon(
                                    painterResource(id = R.drawable.digi_trash),
                                    contentDescription = "increase icon",
                                    tint = MaterialTheme.colors.digikalaRed,
                                    modifier = Modifier.clickable {
                                        viewModel.removeFromCart(item)
                                    }
                                )
                            } else {
                                Icon(
                                    painterResource(id = R.drawable.ic_decrease_24),
                                    contentDescription = "increase icon",
                                    tint = MaterialTheme.colors.digikalaRed,
                                    modifier = Modifier.clickable {
                                        count.value--
                                        viewModel.changeCountCartItem(item.id, count.value)
                                    }
                                )
                            }
                        }
                    } else {
                        Row(
                            modifier = Modifier
                                .clickable {
                                    viewModel.changeStatusCart(item.id, CartStatus.CURRENT_CART)
                                }
                                .padding(
                                    horizontal = 40.dp,
                                    vertical = 4.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painterResource(id = R.drawable.ic_baseline_shopping_cart_checkout),
                                contentDescription = "increase icon",
                                tint = MaterialTheme.colors.digikalaRed,
                                modifier = Modifier
                                    .size(28.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.semiMedium))

                val discount = (item.price * item.discountPercent) / 100

                Column() {
                    Text(
                        text = "${digitByLocateAndSeparator(discount.toString())} ${stringResource(id = R.string.discount)}",
                        style = Typography.extraSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.DigikalaDarkRed
                    )
                    Row {
                        Text(
                            text = digitByLocateAndSeparator(item.price.toString()),
                            style = Typography.h3,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.darkText
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.toman),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(horizontal = LocalSpacing.current.extraSmall)
                        )
                    }
                }
            }

            if (mode == CartStatus.CURRENT_CART) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text(
                        text = stringResource(R.string.save_to_next_list),
                        fontWeight = FontWeight.Light,
                        style = Typography.h6,
                        color = MaterialTheme.colors.DarkCyan,
                        modifier = Modifier.clickable {
                            viewModel.changeStatusCart(item.id, CartStatus.NEXT_CART)
                        }
                    )


                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "",
                        tint = MaterialTheme.colors.DarkCyan,
                        modifier = Modifier.clickable {
                            viewModel.changeStatusCart(item.id, CartStatus.NEXT_CART)
                        }
                    )

                }
            } else {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text(
                        text = stringResource(R.string.delete_from_list),
                        fontWeight = FontWeight.Light,
                        style = Typography.h6,
                        color = MaterialTheme.colors.digikalaRed,
                        modifier = Modifier.clickable {
                            viewModel.removeFromCart(item)
                        }
                    )


                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "",
                        tint = MaterialTheme.colors.digikalaRed,
                        modifier = Modifier.clickable {
                            viewModel.removeFromCart(item)
                        }
                    )

                }
            }

        }
    }
}

@Composable
fun DetailRow(
    icon: Painter,
    text: String,
    color: Color,
    fontStyle: TextStyle,
) {
    Row(
        modifier = Modifier
            .padding(vertical = LocalSpacing.current.extraSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = icon,
            contentDescription = "",
            modifier = Modifier
                .size(16.dp),
            tint = color,
        )

        Spacer(modifier = Modifier.width(LocalSpacing.current.small))

        Text(
            text = text,
            style = fontStyle,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.semiDarkText,

            )

    }
}
