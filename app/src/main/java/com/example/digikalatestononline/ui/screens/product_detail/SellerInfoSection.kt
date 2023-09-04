package com.example.digikalatestononline.ui.screens.product_detail

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikalatestononline.R
import com.example.digikalatestononline.ui.screens.basket.DetailRow
import com.example.digikalatestononline.ui.theme.DarkCyan
import com.example.digikalatestononline.ui.theme.DigikalaLightGreen
import com.example.digikalatestononline.ui.theme.DigikalaLightRed
import com.example.digikalatestononline.ui.theme.LocalShape
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.extraSmall
import com.example.digikalatestononline.ui.theme.grayCategory
import com.example.digikalatestononline.ui.theme.h4
import com.example.digikalatestononline.ui.theme.h5
import com.example.digikalatestononline.ui.theme.h6
import com.example.digikalatestononline.ui.theme.semiDarkText
import com.example.digikalatestononline.ui.theme.unSelectedBottomBar
import com.example.digikalatestononline.util.DigitHelper


@Composable
fun SellerInfoSection(){
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalSpacing.current.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                vertical = LocalSpacing.current.small,
                horizontal = LocalSpacing.current.medium,
            )

    ) {


        Spacer(modifier = Modifier.height(LocalSpacing.current.extraSmall))

        Text(
            text = stringResource(id = R.string.seller),
            style = Typography.h4,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = LocalSpacing.current.small)
        )

        Spacer(modifier = Modifier.width(LocalSpacing.current.small))

        Row(
            modifier = Modifier.padding(
                top = LocalSpacing.current.small,
                start = LocalSpacing.current.small,
                end = LocalSpacing.current.small
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.digi_logo),
                modifier = Modifier
                    .size(25.dp)
                    .clip(LocalShape.current.small), contentDescription = ""
            )
            Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

            Column {
                Text(
                    text = stringResource(id = R.string.digikala),
                    style = Typography.h5,
                    color = MaterialTheme.colors.darkText,
                )
                Spacer(modifier = Modifier.height(LocalSpacing.current.small))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${DigitHelper.digitByLocate("101")}%" +
                                " رضایت خریداران | عملکرد ",
                        style = Typography.h6,
                        color = MaterialTheme.colors.semiDarkText,
                    )
                }
                Spacer(
                    modifier = Modifier
                        .padding(top = LocalSpacing.current.small)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colors.grayCategory)
                )
            }

        }


        Row(
            modifier = Modifier.padding(
                bottom = LocalSpacing.current.semiMedium,
                start = LocalSpacing.current.small,
                end = LocalSpacing.current.small
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.guarantee),
                modifier = Modifier
                    .padding(bottom = LocalSpacing.current.small)
                    .size(25.dp)
                    .clip(LocalShape.current.small), contentDescription = ""
            )

            Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

            Column {
                Text(
                    modifier = Modifier
                        .padding(vertical = LocalSpacing.current.small),
                    text = stringResource(id = R.string.productWarranty),
                    style = Typography.h5,
                    color = MaterialTheme.colors.darkText,
                )
                Spacer(
                    modifier = Modifier
                        .padding(top = LocalSpacing.current.small)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colors.grayCategory)
                )
            }

        }



        Row(
            modifier = Modifier
                .padding(horizontal = LocalSpacing.current.biggerSmall)
        )
        {
            Column(
                modifier = Modifier
                    .width(16.dp)
                    .fillMaxHeight()
                    .padding(
                        vertical = LocalSpacing.current.small,
                    ),
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

            Column(Modifier.padding(horizontal = LocalSpacing.current.biggerMedium)) {

                Text(
                    text = stringResource(id = R.string.in_digi_stock),
                    style = Typography.h6,
                    color = MaterialTheme.colors.semiDarkText,
                    modifier = Modifier
                        .padding(vertical = LocalSpacing.current.extraSmall),
                )

                DetailRow(
                    painterResource(id = R.drawable.k1),
                    stringResource(id = R.string.digikala_send),
                    color = MaterialTheme.colors.DigikalaLightRed,
                    fontStyle = Typography.extraSmall
                )

                DetailRow(
                    painterResource(id = R.drawable.k2),
                    stringResource(id = R.string.fast_send),
                    color = MaterialTheme.colors.DigikalaLightGreen,
                    fontStyle = Typography.extraSmall
                )

            }
        }


        Spacer(
            modifier = Modifier
                .padding(top = LocalSpacing.current.small)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.grayCategory)
        )




        Row(
            modifier = Modifier.padding(
                horizontal = LocalSpacing.current.small
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.digi_score),
                modifier = Modifier
                    .size(20.dp)
                    .clip(LocalShape.current.small), contentDescription = ""
            )

            Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

            Column {
                Text(
                    modifier = Modifier
                        .padding(vertical = LocalSpacing.current.small),
                    text = stringResource(id = R.string.digiclub_get_score),
                    style = Typography.h5,
                    color = MaterialTheme.colors.darkText,
                )

            }

        }


        Spacer(
            modifier = Modifier
                .padding(top = LocalSpacing.current.small)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.grayCategory)
        )



        Row(
            modifier = Modifier.padding(
                bottom = LocalSpacing.current.small,
                start = LocalSpacing.current.small,
                end = LocalSpacing.current.small
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.info),
                modifier = Modifier
                    .size(20.dp)
                    .clip(LocalShape.current.small), contentDescription = ""
            )

            Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

            Column {
                Text(
                    modifier = Modifier
                        .padding(vertical = LocalSpacing.current.small),
                    text = "${stringResource(id = R.string.manufacturer_price)} 111 ${
                        stringResource(
                            id = R.string.toman
                        )
                    }",
                    style = Typography.h5,
                    color = MaterialTheme.colors.unSelectedBottomBar,
                )
            }

        }


        Spacer(modifier = Modifier.height(LocalSpacing.current.extraSmall))


        Row(
            modifier = Modifier
                .padding(
                    start = LocalSpacing.current.small,
                    end = LocalSpacing.current.small
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(id = R.string.better_price_suggestion),
                style = Typography.extraSmall,
                color = MaterialTheme.colors.unSelectedBottomBar,
            )

            Image(
                painter = painterResource(id = R.drawable.mark),
                modifier = Modifier
                    .size(25.dp), contentDescription = ""
            )
        }

    }
}