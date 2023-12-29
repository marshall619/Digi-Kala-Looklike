package com.example.digikalatestononline.ui.screens.product_detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.product_detail.Comment
import com.example.digikalatestononline.ui.components.Loading3Dots
import com.example.digikalatestononline.ui.components.OurLoading
import com.example.digikalatestononline.ui.theme.Green
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Oranges
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.grayAlpha
import com.example.digikalatestononline.ui.theme.h3
import com.example.digikalatestononline.ui.theme.h5
import com.example.digikalatestononline.ui.theme.h6
import com.example.digikalatestononline.ui.theme.semiDarkText
import com.example.digikalatestononline.util.DigitHelper
import com.example.digikalatestononline.viewmodel.ProductDetailViewModel

@Composable
fun AllCommentProductScreen(
    viewModel: ProductDetailViewModel = hiltViewModel(),
    navController: NavController,
    productId: String,
    commentsCount: String
) {

    LaunchedEffect(true){
        viewModel.getCommentList(productId = productId)
    }

    val commentList = viewModel.commentList.collectAsLazyPagingItems()

    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = LocalSpacing.current.medium)
                    .clickable {
                        navController.popBackStack()
                    }
                    .size(24.dp)

            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "${DigitHelper.digitByLocate(commentsCount)} ${stringResource(id = R.string.comment)}",
                textAlign = TextAlign.Start,
                style = Typography.h3,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText,
            )

        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(commentList) { comments ->
                if (comments != null) {
                    CommentsItem(item = comments)
                }
            }

            commentList.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            val config = LocalConfiguration.current
                            OurLoading(height = config.screenHeightDp.dp, isDark = true)
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Loading3Dots(isDark = true)
                            }
                        }
                    }
                }
            }
        }

    }
}

@Composable
private fun CommentsItem(item: Comment) {

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.medium)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LocalSpacing.current.medium),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .width(LocalSpacing.current.large)
                    .background(colorSuggestion),
                text = DigitHelper.digitByLocate(item.star.toString() + ".0"),
                style = Typography.h6,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(start = LocalSpacing.current.medium),
                text = DigitHelper.digitByLocate(DigitHelper.gregorianToJalali(year, month, day)),
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

        Divider(
            modifier = Modifier
                .padding(start = LocalSpacing.current.large)
                .fillMaxWidth()
                .height(1.dp)
                .alpha(0.4f)
                .shadow(2.dp),
            color = Color.LightGray,
        )


        Row(
            modifier = Modifier
                .padding(
                    vertical = LocalSpacing.current.medium,
                    horizontal = LocalSpacing.current.large
                ),
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
            modifier = Modifier
                .padding(
                    start = LocalSpacing.current.large
                ),
            text = item.title,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = Typography.h5,
        )
        Text(
            modifier = Modifier
                .padding(
                    start = LocalSpacing.current.large,
                    top = LocalSpacing.current.small,
                    bottom = LocalSpacing.current.large,
                ),
            text = item.description,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = Typography.h5,
        )


    }
}
