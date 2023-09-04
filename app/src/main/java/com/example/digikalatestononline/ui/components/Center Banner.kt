package com.example.digikalatestononline.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikalatestononline.ui.theme.LocalShape
import com.example.digikalatestononline.ui.theme.LocalSpacing

@Composable
fun CenterBannerItem(url: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(LocalSpacing.current.medium),
        shape = LocalShape.current.semiMedium
    ) {
        Image(
            painter = rememberAsyncImagePainter(url),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun CenterBannerItem(url: Painter) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(LocalSpacing.current.medium),
        shape = LocalShape.current.semiMedium
    ) {
        Image(
            painter = url,
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}