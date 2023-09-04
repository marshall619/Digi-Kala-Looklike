package com.example.digikalatestononline.data.model.basket

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digikalatestononline.util.Constants.SHOPPING_CART_TABLE

@Entity(tableName = SHOPPING_CART_TABLE)
data class CartItem(
    @PrimaryKey
    val id: String,
    val discountPercent: Int,
    val image: String,
    val name: String,
    val price: Long,
    val seller: String,
    val count : Int,
    val cartStatus : CartStatus
)
