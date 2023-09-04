package com.example.digikalatestononline.ui.screens.basket

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.digikalatestononline.R
import com.example.digikalatestononline.navigation.Screen
import com.example.digikalatestononline.ui.theme.LocalElevation
import com.example.digikalatestononline.ui.theme.LocalShape
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.amber
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.h5
import com.example.digikalatestononline.ui.theme.h6

@Composable
fun LoginOrRegisterSection(navController: NavController){
    Card(
        modifier = Modifier
            .padding(LocalSpacing.current.medium)
            .clickable {navController.navigate(Screen.Profile.route) },
        shape = LocalShape.current.small,
        elevation = LocalElevation.current.extraSmall ,
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ){

            Icon(
                painter = painterResource(id = R.drawable.import_96_orenge),
                contentDescription = "",
                tint = MaterialTheme.colors.amber,
                modifier = Modifier
                    .size(30.dp)
                    .weight(0.1f)
                    .align(Alignment.Top)
            )

            Spacer(modifier = Modifier.weight(0.05f))

            Column(
                modifier = Modifier.weight(0.8f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ){
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(R.string.login_or_register),
                    textAlign = TextAlign.Start,
                    style = Typography.h5,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.darkText,
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(R.string.login_or_register_msg),
                    textAlign = TextAlign.Start,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold,
                    style = Typography.h6,
                )
            }

            Spacer(modifier = Modifier.weight(0.05f))

            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .size(22.dp)
                    .weight(0.1f)
                    .align(Alignment.Top)
                    .padding(top = LocalSpacing.current.small)

            )

        }
    }

}