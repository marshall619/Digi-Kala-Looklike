package com.example.digikalatestononline.ui.screens.product_detail

import androidx.compose.runtime.Composable
import com.example.digikalatestononline.data.model.product_detail.ProductColor
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.digikalatestononline.ui.theme.CursorColor
import com.example.digikalatestononline.ui.theme.LocalShape
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.h6


@Composable
fun ColorChipItem(item: ProductColor, isSelected: Boolean, onSelectColor: (String) -> Unit) {
    Surface(
        modifier =
        if (isSelected)
            Modifier
                .padding(LocalSpacing.current.extraSmall)
                .border(1.dp, MaterialTheme.colors.CursorColor, CircleShape)
        else
            Modifier.padding(LocalSpacing.current.extraSmall),
        elevation = 1.dp,
        shape = LocalShape.current.biggerMedium,
    ) {

        Row(
            modifier = Modifier
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        onSelectColor(item.color)
                    }
                )
                .padding(LocalSpacing.current.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Canvas(
                modifier = Modifier
                    .size(20.dp)
                    .border(1.dp, Color.Gray, CircleShape),
                onDraw = {
                    drawCircle(Color(("ff" + item.code.removePrefix("#").lowercase()).toLong(16)))
                }
            )

            Spacer(modifier = Modifier.width(LocalSpacing.current.small))

            Text(
                text = item.color,
                style = Typography.h6
            )

        }

    }

}