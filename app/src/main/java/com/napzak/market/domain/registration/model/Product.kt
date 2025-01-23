package com.napzak.market.domain.registration.model

interface Product

data class SellProduct(
    val imageUrls: List<ProductImage>,
    val genreId: Long,
    val title: String,
    val description: String,
    val price: Int,
    val productCondition: String?,
    val isDeliveryIncluded: Boolean,
    val standardDeliveryFee: Int,
    val halfDeliveryFee: Int,
) : Product

data class BuyProduct(
    val imageUrls: List<ProductImage>,
    val genreId: Long,
    val title: String,
    val description: String,
    val price: Int,
    val isPriceNegotiable: Boolean,
) : Product

data class ProductImage(
    val imageUrl: String,
    val sequence: Int,
)
