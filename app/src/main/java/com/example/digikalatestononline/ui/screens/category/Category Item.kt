package com.example.digikalatestononline.ui.screens.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.category.Sub
import com.example.digikalatestononline.ui.theme.LightCyan
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.h3
import com.example.digikalatestononline.ui.theme.h5

@Composable
fun CategoryItem(title: String , itemList: List<Sub>) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = LocalSpacing.current.medium,
                bottom = LocalSpacing.current.small,
                start = LocalSpacing.current.small,
                end = LocalSpacing.current.small
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = Typography.h3, fontWeight = FontWeight.Bold)
        Text(
            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
            text = stringResource(id = R.string.see_all),
            style = Typography.h5,
            color = MaterialTheme.colors.LightCyan
        )
    }

    LazyRow(){
        items(itemList){item ->
            SubCategoryItem(item)
        }
    }

}