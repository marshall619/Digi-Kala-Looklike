package com.example.digikalatestononline.data.model.home

data class StoreProducts(
    val _id: String,
    val discountPercent: Int,
    val image: String,
    val name: String,
    val price: Long,
    val seller: String
)