package com.example.digikalatestononline.ui.screens.product_detail

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikalatestononline.R
import com.example.digikalatestononline.navigation.Screen
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.extraSmall
import com.example.digikalatestononline.ui.theme.grayCategory
import com.example.digikalatestononline.ui.theme.h4
import com.example.digikalatestononline.ui.theme.h5
import com.example.digikalatestononline.ui.theme.settingArrow
import com.example.digikalatestononline.ui.theme.unSelectedBottomBar


@Composable
fun ProductDescriptionSection(
    navController: NavController,
    description : String,
    technicalFeatures : String
){
    var isDescription by remember { mutableStateOf(true) }
    if (description.isBlank()) {
        isDescription = false
    }

    var isTechnicalFeatures  by remember { mutableStateOf(true) }
    if (technicalFeatures == "null") {
        isTechnicalFeatures = false
    }


    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalSpacing.current.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )

    Text(
        modifier = Modifier
            .padding(LocalSpacing.current.small),
        text = stringResource(id = R.string.product_desc),
        style = Typography.h4,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colors.darkText,
    )

    Spacer(
        modifier = Modifier
            .padding(LocalSpacing.current.medium)
            .fillMaxWidth()
            .height(1.dp)
            .background(MaterialTheme.colors.grayCategory)
    )

    if (isTechnicalFeatures){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(Screen.ProductTechnicalFeaturesScreen.withArgs(technicalFeatures))
                }
                .padding(horizontal = LocalSpacing.current.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                style = Typography.h5,
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.technical_specifications),
                color = MaterialTheme.colors.darkText,
            )
            Icon(
                Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colors.settingArrow
            )
        }
    }

    if (isDescription) {
        Spacer(
            modifier = Modifier
                .padding(LocalSpacing.current.medium)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.grayCategory)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(Screen.ProductDescription.withArgs(description))
                }
                .padding(horizontal = LocalSpacing.current.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                style = Typography.h5,
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.product_introduce),
                color = MaterialTheme.colors.darkText,
            )
            Icon(
                Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colors.settingArrow
            )
        }
    }




    Row(
        modifier = Modifier
            .padding(
                LocalSpacing.current.semiMedium,
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
            text = stringResource(id = R.string.product_desc_feedback),
            style = Typography.extraSmall,
            color = MaterialTheme.colors.unSelectedBottomBar,
        )

        Image(
            painter = painterResource(id = R.drawable.info),
            modifier = Modifier
                .size(20.dp), contentDescription = ""
        )
    }

}
