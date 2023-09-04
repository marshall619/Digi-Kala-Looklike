package com.example.digikalatestononline.ui.screens.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.product_detail.ProductDetail
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

@Composable
fun ProductDetailBottomBar(
    item: ProductDetail,
    navController: NavController,
){
    var price = 0L
    item.price?.let {
        price = it
    }
    var discountPercent = 0
    item.discountPercent?.let {
        discountPercent = it
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

            Button(
                onClick = {},
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