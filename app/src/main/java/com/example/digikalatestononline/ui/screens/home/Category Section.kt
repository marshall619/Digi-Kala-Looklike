package com.example.digikalatestononline.ui.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.home.CategoryModel
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.h2
import com.example.digikalatestononline.ui.theme.h6
import com.example.digikalatestononline.viewmodel.HomeViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategorySection(viewModel: HomeViewModel = hiltViewModel()) {
    var categorys by remember { mutableStateOf<List<CategoryModel>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }

    val categoryResult by viewModel.category.collectAsState()

    when (categoryResult) {
        is NetworkResult.Success -> {
            categorys = categoryResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            Log.e("6191", "amazingItem Api Error is :${categoryResult.message}")
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(LocalSpacing.current.small)
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.medium),
            text = stringResource(id = R.string.category_title),
            textAlign = TextAlign.Center,
            style = Typography.h2,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText,
        )

        FlowRow(
            horizontalArrangement = Arrangement.SpaceAround,
            maxItemsInEachRow = 3,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            for (item in categorys) {
                CircularCategoryItem(item)
            }
        }
    }
}


@Composable
fun CircularCategoryItem(item : CategoryModel){
    Column(
        modifier = Modifier.size(100.dp , 160.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Image(
            painter = rememberAsyncImagePainter(item.image),
            contentDescription = "",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .padding(vertical = LocalSpacing.current.extraSmall)
        )

        Text(
            text = item.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = LocalSpacing.current.extraSmall),
            textAlign = TextAlign.Center,
            style = Typography.h6,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}