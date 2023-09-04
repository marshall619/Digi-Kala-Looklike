package com.example.digikalatestononline.ui.screens.product_detail

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.R
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.h3
import com.example.digikalatestononline.ui.theme.h4
import com.example.digikalatestononline.ui.theme.h6
import com.example.digikalatestononline.ui.theme.searchBarBg
import com.example.digikalatestononline.ui.theme.semiDarkText


@Composable
fun ProductDescriptionScreen(
    navController: NavController,
    description : String
){
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
                text = stringResource(id = R.string.review),
                textAlign = TextAlign.Start,
                style = Typography.h3,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText,
            )

        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(MaterialTheme.colors.searchBarBg)
        )


        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.semiMedium),
                text = stringResource(id = R.string.product_introduce),
                textAlign = TextAlign.Start,
                style = Typography.h4,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText,
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.semiMedium),
                text = description,
                textAlign = TextAlign.Start,
                style = Typography.h6,
                color = MaterialTheme.colors.semiDarkText,
            )
        }

    }
}