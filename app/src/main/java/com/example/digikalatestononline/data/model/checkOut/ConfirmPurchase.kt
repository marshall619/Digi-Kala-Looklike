package com.example.digikalatestononline.data.model.checkOut

data class ConfirmPurchase(
    val orderId: String,
    val token: String,
    val transactionId: String
)
