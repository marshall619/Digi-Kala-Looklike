package com.example.digikalatestononline.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikalatestononline.R
import com.example.digikalatestononline.ui.theme.LocalElevation
import com.example.digikalatestononline.ui.theme.LocalShape
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.h2
import com.example.digikalatestononline.ui.theme.searchBarBg
import com.example.digikalatestononline.ui.theme.unSelectedBottomBar
import com.example.digikalatestononline.util.Constants.PERSIAN_LANG
import com.example.digikalatestononline.util.Constants.USER_LANGUAGE

@Composable
fun SearchBarSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
        elevation = LocalElevation.current.extraSmall
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalSpacing.current.extraSmall)
                .clip(LocalShape.current.biggerSmall)
                .background(MaterialTheme.colors.searchBarBg)
        ) {
            SearchBarContent()
        }

    }
}

@Composable
private fun SearchBarContent() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.search),
            contentDescription = "search",
            modifier = Modifier.height(24.dp)
        )
        
        Text(
            text = stringResource(id = R.string.search_in),
            modifier = Modifier
                .padding(start = 20.dp),
            textAlign = TextAlign.Center,
            style = Typography.h2,
            color = MaterialTheme.colors.unSelectedBottomBar
        )

        Image(painter =  digikalaLogoChangeByLanguage(), contentDescription = "", modifier = Modifier
            .width(80.dp)
            .padding(5.dp))
    }
}


@Composable
private fun digikalaLogoChangeByLanguage() : Painter{
    return  if (USER_LANGUAGE == PERSIAN_LANG){
        painterResource(id = R.drawable.digi_red_persian)
    }else{
        painterResource(id = R.drawable.digi_red_english)
    }
}

