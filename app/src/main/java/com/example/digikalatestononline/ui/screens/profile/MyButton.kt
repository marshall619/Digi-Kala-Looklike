package com.example.digikalatestononline.ui.screens.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.digikalatestononline.ui.theme.LocalShape
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.digikalaRed
import com.example.digikalatestononline.ui.theme.h5

@Composable
fun MyButton(
    text: String,
    onClick: () -> Unit,
){
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.digikalaRed),
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(
                start = LocalSpacing.current.semiLarge,
                end = LocalSpacing.current.semiLarge,
                bottom = LocalSpacing.current.medium
            ),
        shape = LocalShape.current.small
    ) {
        Text(
            text = text,
            color = Color.White,
            style = Typography.h5
        )
    }
}