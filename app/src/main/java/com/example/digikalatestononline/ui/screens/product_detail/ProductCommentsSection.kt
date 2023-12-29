package com.example.digikalatestononline.ui.screens.product_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.digikalatestononline.data.model.product_detail.Comment
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.R
import com.example.digikalatestononline.navigation.Screen
import com.example.digikalatestononline.ui.theme.LightCyan
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.h3
import com.example.digikalatestononline.ui.theme.h4
import com.example.digikalatestononline.util.DigitHelper.digitBySeparator

@Composable
fun ProductCommentsSection(
    comments: List<Comment>,
    commentCount: Int,
    productId : String,
    navController: NavController
){
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
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.semiLarge),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.user_comments),
                color = MaterialTheme.colors.darkText,
                fontWeight = FontWeight.Bold,
                style = Typography.h3,
            )
            Text(
                text = "${digitBySeparator(commentCount.toString())} " + stringResource(R.string.comment),
                color = MaterialTheme.colors.LightCyan,
                style = Typography.h4,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.AllComment.withArgs(productId , commentCount))
                }
            )
        }
    }


    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LocalSpacing.current.medium),
    ){
        items(comments){ comment ->
            TextCommentCard(comment)
        }
        item {
            AllCommentShowMoreSection(productId, navController)
        }
    }

}