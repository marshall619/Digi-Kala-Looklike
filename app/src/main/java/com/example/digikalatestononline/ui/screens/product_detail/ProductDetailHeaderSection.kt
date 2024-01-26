package com.example.digikalatestononline.ui.screens.product_detail

import androidx.compose.runtime.Composable
import com.example.digikalatestononline.data.model.product_detail.SliderImage
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikalatestononline.data.model.product_detail.ProductDetail
import com.example.digikalatestononline.ui.theme.DarkCyan
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.h6
import com.example.digikalatestononline.R
import com.example.digikalatestononline.ui.theme.DigikalaLightGreen
import com.example.digikalatestononline.ui.theme.Gold
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.grayAlpha
import com.example.digikalatestononline.ui.theme.grayCategory
import com.example.digikalatestononline.ui.theme.h3
import com.example.digikalatestononline.ui.theme.semiDarkText
import com.example.digikalatestononline.util.DigitHelper.digitByLocate

@Composable
fun ProductDetailHeaderSection( item: ProductDetail){
    Column {
        Text(
            text = "${stringResource(id = R.string.category)} / ${item.category}",
            color = MaterialTheme.colors.DarkCyan,
            style = Typography.h6,
            modifier = Modifier.padding(horizontal = LocalSpacing.current.medium)
        )

        Text(
            text = item.name.toString(),
            color = MaterialTheme.colors.darkText,
            style = Typography.h3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                horizontal = LocalSpacing.current.medium,
                vertical = LocalSpacing.current.small
            ),
            maxLines = 2
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = LocalSpacing.current.medium)
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "",
                modifier = Modifier.size(15.dp),
                tint = MaterialTheme.colors.Gold
            )
            Text(
                text = digitByLocate(item.star.toString()),
                color = MaterialTheme.colors.semiDarkText,
                style = Typography.h6,
                modifier = Modifier.padding(horizontal = 2.dp)
            )
            Text(
                text = digitByLocate("(${item.starCount})"),
                color = MaterialTheme.colors.grayAlpha,
                style = Typography.h6,
                modifier = Modifier.padding(end = LocalSpacing.current.small)
            )

            Icon(
                painter = painterResource(id = R.drawable.dot),
                contentDescription = "",
                tint = MaterialTheme.colors.grayAlpha,
                modifier = Modifier
                    .size(7.dp)
                    .padding(horizontal = 1.dp)
            )

            Text(
                text = digitByLocate("${item.commentCount} ${stringResource(R.string.user_comments)}"),
                color = MaterialTheme.colors.DarkCyan,
                style = Typography.h6,
                modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
            )

            Icon(
                painter = painterResource(id = R.drawable.dot),
                contentDescription = "",
                tint = MaterialTheme.colors.grayAlpha,
                modifier = Modifier
                    .size(7.dp)
                    .padding(horizontal = 1.dp)
            )
            Text(
                text = digitByLocate("${item.viewCount} ${stringResource(R.string.view)}"),
                color = MaterialTheme.colors.DarkCyan,
                style = Typography.h6,
                modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    horizontal = LocalSpacing.current.medium,
                    vertical = LocalSpacing.current.small,
                )
                .fillMaxWidth()
        ){
            Icon(
                painter = painterResource(id = R.drawable.like),
                contentDescription = "",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colors.DigikalaLightGreen
            )
            val percent = ((item.star?.div(5.0) ?: 0.0) * 100).toInt()
            val users = (percent * (item.starCount?.toDouble() ?: 0.0) / 100).toInt()

            val text = String.format(
                "%d%% (%d نفر) از خریداران این کالا را پیشنهاد کرده اند.",
                percent,
                users
            )


            Text(
                text = digitByLocate(text),
                color = MaterialTheme.colors.semiDarkText,
                style = Typography.h6,
                modifier = Modifier.padding(horizontal = LocalSpacing.current.small)
            )
        }

        Divider(
            color = MaterialTheme.colors.grayCategory,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = LocalSpacing.current.medium))


    }

}