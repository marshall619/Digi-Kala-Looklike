package com.example.digikalatestononline.ui.screens.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikalatestononline.data.model.product_detail.ProductDetail
import com.example.digikalatestononline.navigation.Screen
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.util.Constants
import com.example.digikalatestononline.R
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.grayCategory
import com.example.digikalatestononline.ui.theme.h5
import com.example.digikalatestononline.ui.theme.h6
import com.example.digikalatestononline.ui.theme.semiDarkText
import com.example.digikalatestononline.ui.theme.settingArrow


@Composable
fun ProductSetCommentsSection(
    navController: NavHostController,
    item : ProductDetail
){
    Column(
        modifier = Modifier
            .padding(
                horizontal = LocalSpacing.current.semiLarge,
                vertical = LocalSpacing.current.medium
            )
            .clickable {
                if (Constants.USER_TOKEN == "null") {
                    navController.navigate(Screen.Profile.route)
                }else{
                    navController.navigate(Screen.NewComment.route + "?productId=${item._id}?productName=${item.name}?imageUrl=${item.imageSlider!![0].image}")
                }

            }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                painter = painterResource(
                    id = R.drawable.comment
                ),
                contentDescription = "",
                Modifier.size(20.dp),
            )

            Text(
                text = stringResource(id = R.string.write_your_comment),
                Modifier
                    .weight(1f)
                    .padding(start = 20.dp),
                color = MaterialTheme.colors.darkText,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                style = Typography.h5,
            )

            Icon(
                Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colors.settingArrow
            )
        }

        Text(
            text = stringResource(R.string.comment_desc),
            Modifier
                .padding(start = LocalSpacing.current.large + LocalSpacing.current.small),
            color = MaterialTheme.colors.semiDarkText,
            style = Typography.h6,
        )

        Spacer(
            modifier = Modifier
                .padding(
                    start = LocalSpacing.current.large,
                    top = LocalSpacing.current.medium,
                )
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.grayCategory)

        )

    }
}