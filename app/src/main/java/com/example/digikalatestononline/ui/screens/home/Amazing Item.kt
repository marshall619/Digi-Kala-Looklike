package com.example.digikalatestononline.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.home.AmazingItem
import com.example.digikalatestononline.navigation.Screen
import com.example.digikalatestononline.ui.theme.DarkCyan
import com.example.digikalatestononline.ui.theme.DigikalaDarkRed
import com.example.digikalatestononline.ui.theme.DigikalaLightRed
import com.example.digikalatestononline.ui.theme.DigikalaLightRedText
import com.example.digikalatestononline.ui.theme.LocalShape
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.body2
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.extraSmall
import com.example.digikalatestononline.ui.theme.h6
import com.example.digikalatestononline.ui.theme.semiDarkText
import com.example.digikalatestononline.util.Constants
import com.example.digikalatestononline.util.DigitHelper.applyDiscount
import com.example.digikalatestononline.util.DigitHelper.digitByLocate
import com.example.digikalatestononline.util.DigitHelper.digitByLocateAndSeparator

@Composable
fun AmazingItem(item: AmazingItem , navController: NavController) {
    Card(
        modifier = Modifier
            .width(170.dp)
            .padding(
                horizontal = LocalSpacing.current.semiSmall,
                vertical = LocalSpacing.current.semiLarge,
            ).clickable {
                navController.navigate(Screen.ProductDetail.withArgs(item._id))
            },
        shape = LocalShape.current.small
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.small)
        ) {


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = LocalSpacing.current.semiSmall)
            ) {
                Text(
                    text = stringResource(id = R.string.amazing_for_app),
                    modifier = Modifier.padding(start = LocalSpacing.current.small),
                    style = Typography.extraSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.DigikalaLightRedText
                )

                Spacer(modifier = Modifier.height(10.dp))

                Image(
                    painter = rememberAsyncImagePainter(model = item.image),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp),
                    contentScale = ContentScale.FillBounds
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = LocalSpacing.current.small)
            ) {
                Text(
                    text = item.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(48.dp)
                        .padding(horizontal = LocalSpacing.current.small),
                    style = Typography.h6,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.darkText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.in_stock),
                        contentDescription = "",
                        modifier = Modifier
                            .size(22.dp)
                            .padding(2.dp),
                        tint = MaterialTheme.colors.DarkCyan
                    )

                    Text(
                        text = item.seller,
                        style = Typography.extraSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.semiDarkText
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalSpacing.current.small),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ){

                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(24.dp)
                        .background(
                            color = MaterialTheme.colors.DigikalaDarkRed,
                            shape = CircleShape
                        )
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .wrapContentHeight(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "${digitByLocateAndSeparator(item.discountPercent.toString())}%",
                        color = Color.White,
                        style = Typography.h6,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Column {

                    Row {
                        Text(
                            text = digitByLocateAndSeparator(applyDiscount(item.price , item.discountPercent).toString()),
                            style = Typography.body2,
                            fontWeight = FontWeight.SemiBold,
                        )

                        Icon(
                            painter = amazingItemLogoChangeByLanguage(),
                            contentDescription = "",
                            modifier = Modifier
                                .size(LocalSpacing.current.semiLarge)
                                .padding(horizontal = LocalSpacing.current.extraSmall)
                        )

                    }

                    Text(
                        text = digitByLocateAndSeparator(item.price.toString()),
                        color = Color.LightGray,
                        style = Typography.body2,
                        textDecoration = TextDecoration.LineThrough
                    )
                }
            }
        }
    }
}


@Composable
private fun amazingItemLogoChangeByLanguage() : Painter {
    return  if (Constants.USER_LANGUAGE == Constants.PERSIAN_LANG){
        painterResource(id = R.drawable.toman)
    }else{
        painterResource(id = R.drawable.dollar)
    }
}