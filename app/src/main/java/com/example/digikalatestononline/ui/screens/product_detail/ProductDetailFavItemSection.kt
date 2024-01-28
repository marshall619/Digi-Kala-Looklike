package com.example.digikalatestononline.ui.screens.product_detail

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikalatestononline.data.model.profile.FavItem
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.viewmodel.FavoriteItemViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductDetailFavItemSection(
    favItem: FavItem ,
    viewModel : FavoriteItemViewModel = hiltViewModel()
){
    var checkedState by remember { mutableStateOf(false) }

    LaunchedEffect(true){
        viewModel.isFavItemExist(favItem.id).collectLatest {
            checkedState = it
        }
    }

    IconToggleButton(
        checked = checkedState,
        onCheckedChange = {
            checkedState = !checkedState
            if (checkedState){
                viewModel.insertFavItem(favItem)
            }else{
                viewModel.deleteFavItem(favItem)
            }
        }) {
        val transition =
            updateTransition(targetState = checkedState, label = "icon transition")
        val tint by transition.animateColor(label = "iconColor") { isChecked ->
            if (isChecked) Color.Red else MaterialTheme.colors.darkText
        }
        Icon(
            imageVector = if (checkedState) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .size(27.dp)
        )
    }
}