package com.example.digikalatestononline.ui.screens.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.basket.CartItem
import com.example.digikalatestononline.data.model.basket.CartStatus
import com.example.digikalatestononline.data.model.product_detail.ProductDetail
import com.example.digikalatestononline.navigation.Screen
import com.example.digikalatestononline.ui.theme.DigikalaDarkRed
import com.example.digikalatestononline.ui.theme.LocalElevation
import com.example.digikalatestononline.ui.theme.LocalShape
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.body1
import com.example.digikalatestononline.ui.theme.body2
import com.example.digikalatestononline.ui.theme.bottomBar
import com.example.digikalatestononline.ui.theme.digikalaRed
import com.example.digikalatestononline.ui.theme.h5
import com.example.digikalatestononline.ui.theme.h6
import com.example.digikalatestononline.util.DigitHelper
import com.example.digikalatestononline.util.DigitHelper.applyDiscount
import com.example.digikalatestononline.util.DigitHelper.digitByLocate
import com.example.digikalatestononline.viewmodel.BasketViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductDetailBottomBar(
    item: ProductDetail,
    navController: NavController,
    viewModel : BasketViewModel = hiltViewModel(),
){
    var price = 0L
    item.price?.let {
        price = it
    }
    var discountPercent = 0
    item.discountPercent?.let {
        discountPercent = it
    }

    var isExistInBasket by remember{ mutableStateOf(false) }
    var isLaunchEffectFinished by remember { mutableStateOf(false)}
    var itemCount by remember { mutableStateOf(0) }

    LaunchedEffect(true){
        viewModel.isItemExistInBasket(item._id ?: "").collectLatest {
            isExistInBasket = it
            isLaunchEffectFinished = true
        }
    }

    LaunchedEffect(true){
        viewModel.getItemsCountInBasket(item._id ?: "").collectLatest {
            itemCount = it
        }
    }

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.bottomBar,
        elevation = LocalElevation.current.small,
        modifier = Modifier.height(70.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = LocalSpacing.current.biggerSmall,
                    horizontal = LocalSpacing.current.medium
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                if (!isExistInBasket && isLaunchEffectFinished){
                    Button(
                        onClick = {
                            isExistInBasket = true
                            if (item.imageSlider?.isNotEmpty() == true){
                                viewModel.insertCartItem(CartItem(
                                    item._id.toString(),
                                    item.discountPercent ?: 0,
                                    item.imageSlider[0].image ,
                                    item.name.toString(),
                                    item.price ?: 0,
                                    item.seller.toString(),
                                    count = 1,
                                    cartStatus = CartStatus.CURRENT_CART
                                ))
                            }
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.digikalaRed),
                        shape = LocalShape.current.small,

                        ) {
                        Text(
                            text = stringResource(R.string.add_to_basket),
                            color = Color.White,
                            style = Typography.h5,
                            modifier = Modifier
                                .padding(
                                    vertical = LocalSpacing.current.extraSmall,
                                )
                        )
                    }
                } else if (isExistInBasket && isLaunchEffectFinished){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(shape = CircleShape)
                                .background(MaterialTheme.colors.DigikalaDarkRed),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = itemCount.toString(),
                                modifier = Modifier.padding(LocalSpacing.current.extraSmall),
                                color = Color.White
                            )
                        }

                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(start = LocalSpacing.current.small)
                        ) {
                            Text(
                                text = stringResource(R.string.in_your_basket),
                                color = Color.LightGray,
                                style = Typography.h5,
                            )
                            Text(
                                text = stringResource(R.string.view_in_cart),
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(Screen.Basket.route)
                                    },
                                color = MaterialTheme.colors.DigikalaDarkRed,
                                style = Typography.h5,
                            )
                        }
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row() {
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colors.DigikalaDarkRed,
                                shape = CircleShape
                            )
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .wrapContentHeight(Alignment.CenterVertically),

                        ) {
                        Text(
                            text = "${DigitHelper.digitByLocate(discountPercent.toString())}%",
                            color = Color.White,
                            style = Typography.h6,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small)
                        )
                    }

                    Spacer(modifier = Modifier.padding(horizontal = LocalSpacing.current.extraSmall))

                    Text(
                        text = DigitHelper.digitByLocateAndSeparator((price).toString()),
                        color = Color.Gray,
                        style = Typography.body2,
                        textDecoration = TextDecoration.LineThrough,
                    )
                }

                Row() {
                    Text(
                        text = DigitHelper.digitByLocateAndSeparator(applyDiscount(price , discountPercent).toString()),
                        style = Typography.body1,
                        fontWeight = FontWeight.SemiBold,
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