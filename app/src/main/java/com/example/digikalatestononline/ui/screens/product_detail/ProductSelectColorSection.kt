package com.example.digikalatestononline.ui.screens.product_detail

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.digikalatestononline.data.model.product_detail.ProductColor
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.h4

@Composable
fun ProductSelectColorSection(colors: List<ProductColor>){

    var colorText by remember { mutableStateOf("انتخاب نشده") }

    Column(modifier = Modifier.padding(LocalSpacing.current.small)) {

        Text(
            text = "رنگ : ${colorText}",
            color = MaterialTheme.colors.darkText,
            modifier = Modifier.padding(LocalSpacing.current.small),
            style = Typography.h4,
            fontWeight = FontWeight.Bold
        )


        LazyRow() {
            items(colors) { productColor ->
                ColorChipItem(productColor , isSelected = colorText == productColor.color){
                    colorText = it
                }
            }
        }
    }

}