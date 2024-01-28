package com.example.digikalatestononline.ui.screens.product_detail

import android.content.Context
import android.content.Intent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikalatestononline.R
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.darkText
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.digikalatestononline.data.model.product_detail.Price
import com.example.digikalatestononline.data.model.product_detail.ProductDetail
import com.example.digikalatestononline.data.model.profile.FavItem
import com.example.digikalatestononline.navigation.Screen
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.h4
import com.example.digikalatestononline.util.DigitHelper
import com.google.gson.Gson

@Composable
fun ProductTopAppBar(
    navController: NavHostController,
    item : ProductDetail
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(horizontal = LocalSpacing.current.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(0.6f),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.exit),
                    contentDescription = "",
                    modifier = Modifier.size(17.dp),
                    tint = MaterialTheme.colors.darkText
                )
            }
        }

        Row(
            modifier = Modifier.weight(0.4f),
        ) {

            IconButton(onClick = {
                navController.navigate(Screen.Basket.route)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.basket),
                    contentDescription = "",
                    modifier = Modifier.size(25.dp),
                    tint = MaterialTheme.colors.darkText
                )
            }

            ProductDetailFavItemSection(
                favItem = FavItem(
                    item._id ?: "",
                    item.discountPercent ?: 0 ,
                    item.imageSlider?.get(0)?.image ?: "" ,
                    item.name ?: "",
                    item.price ?: 0,
                    item.seller ?: "",
                    item.star ?: 0.0
                )
            )

            var expanded by remember { mutableStateOf(false) }
            IconButton(
                onClick = {
                    expanded = true
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.menu_dots),
                    contentDescription = "",
                    modifier = Modifier.size(27.dp),
                    tint = MaterialTheme.colors.darkText
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(MaterialTheme.colors.surface)
            )
            {
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        val priceListString = Gson().toJson(item.priceList)
                        navController.navigate(Screen.ProductDetailChart.route + "?jsonString=${priceListString}")
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = LocalSpacing.current.extraSmall),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.chart),
                            contentDescription = "",
                            modifier = Modifier
                                .size(16.dp),
                            tint = MaterialTheme.colors.darkText
                        )

                        Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                        Text(
                            text = stringResource(R.string.price_chart),
                            style = Typography.h4,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.darkText,
                        )
                    }
                }

                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        shareToSocialMedia(
                            context ,
                            item.name!!,
                            DigitHelper.digitByLocateAndSeparator(item.price!!.toString()),
                            "https//marshall.com"
                        )
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = LocalSpacing.current.extraSmall),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = "",
                            modifier = Modifier
                                .size(16.dp),
                            tint = MaterialTheme.colors.darkText
                        )

                        Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                        Text(
                            text = stringResource(R.string.share_product),
                            style = Typography.h4,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.darkText,
                        )
                    }
                }
            }
        }

    }
}


private fun shareToSocialMedia(
    context: Context,
    productName: String,
    productPrice: String,
    url : String
) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"

    shareIntent.putExtra(
        Intent.EXTRA_TEXT,
        "$productName با قیمت باورنکردنی $productPrice تومان فقط در فروشگاه زیر \n $url"
    )

    context.startActivity(Intent.createChooser(shareIntent , "share to..."))
}

