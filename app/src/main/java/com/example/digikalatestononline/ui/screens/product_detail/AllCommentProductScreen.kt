package com.example.digikalatestononline.ui.screens.product_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun AllCommentProductScreen(
    navController: NavController,
    productId : String
){
    Text(text = productId)
}