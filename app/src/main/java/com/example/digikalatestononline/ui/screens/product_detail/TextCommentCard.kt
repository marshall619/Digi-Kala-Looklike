package com.example.digikalatestononline.ui.screens.product_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.product_detail.Comment
import com.example.digikalatestononline.ui.theme.Green
import com.example.digikalatestononline.ui.theme.LocalShape
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Oranges
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.grayAlpha
import com.example.digikalatestononline.ui.theme.h5
import com.example.digikalatestononline.ui.theme.h6
import com.example.digikalatestononline.ui.theme.semiDarkText
import com.example.digikalatestononline.util.DigitHelper.digitByLocate
import com.example.digikalatestononline.util.DigitHelper.gregorianToJalali


@Composable
fun TextCommentCard(
    item : Comment
){
    val dateParts = item.updatedAt.substringBefore("T").split("-")
    val year = dateParts[0].toInt()
    val month = dateParts[1].toInt()
    val day = dateParts[2].toInt()

    val context = LocalContext.current

    var iconSuggestion = R.drawable.like
    var colorSuggestion = MaterialTheme.colors.Green
    var textSuggestion = context.getString(R.string.good_comment)
    var iconRotation = 0f
    when (item.star) {
        in Int.MIN_VALUE..2 -> {
            iconSuggestion = R.drawable.like
            colorSuggestion = MaterialTheme.colors.Oranges
            textSuggestion = context.getString(R.string.bad_comment)
            iconRotation = 180f
        }
        in 2..3 -> {
            iconSuggestion = R.drawable.info
            colorSuggestion = MaterialTheme.colors.semiDarkText
            textSuggestion = context.getString(R.string.so_so_comment)
            iconRotation = 0f
        }
        in 3..Int.MAX_VALUE -> {
            iconSuggestion = R.drawable.like
            colorSuggestion = MaterialTheme.colors.Green
            textSuggestion = context.getString(R.string.good_comment)
            iconRotation = 0f
        }
    }




    Card(
        modifier = Modifier
            .padding(
                horizontal = LocalSpacing.current.small,
                vertical = LocalSpacing.current.medium,
            )
            .width(260.dp)
            .height(210.dp),
        shape = LocalShape.current.small,
        elevation = 2.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalSpacing.current.medium)
        ) {
            Text(
                text = item.title,
                color = MaterialTheme.colors.darkText,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = Typography.h5,
            )

            Row(
                modifier = Modifier
                    .padding(vertical = LocalSpacing.current.small),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = iconSuggestion),
                    contentDescription = "",
                    Modifier
                        .size(16.dp)
                        .rotate(iconRotation),
                    tint = colorSuggestion
                )
                Text(
                    text = textSuggestion,
                    Modifier
                        .padding(start = LocalSpacing.current.extraSmall),
                    maxLines = 1,
                    style = Typography.h6,
                    color = colorSuggestion
                )
            }


            Text(
                text = item.description,
                Modifier.weight(1f),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = Typography.h6,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = digitByLocate(gregorianToJalali(year, month, day)),
                    color = MaterialTheme.colors.semiDarkText,
                    style = Typography.h6,
                )
                Icon(
                    painter = painterResource(id = R.drawable.dot),
                    contentDescription = "",
                    Modifier
                        .size(20.dp)
                        .padding(horizontal = LocalSpacing.current.small),
                    tint = MaterialTheme.colors.grayAlpha
                )
                Text(
                    text = item.userName,
                    color = MaterialTheme.colors.grayAlpha,
                    style = Typography.h6
                )
            }

        }
    }

}