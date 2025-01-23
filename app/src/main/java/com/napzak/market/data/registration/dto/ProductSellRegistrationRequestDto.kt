package com.napzak.market.data.registration.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductSellRegistrationRequestDto(
    @SerialName("productPhotoList")
    val productPhotoDto: List<ProductPhotoDto>,
    @SerialName("genreId")
    val genreId: Long,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: Int,
    @SerialName("productCondition")
    val productCondition: String,
    @SerialName("isDeliveryIncluded")
    val isDeliveryIncluded: Boolean,
    @SerialName("standardDeliveryFee")
    val standardDeliveryFee: Int,
    @SerialName("halfDeliveryFee")
    val halfDeliveryFee: Int,
)
