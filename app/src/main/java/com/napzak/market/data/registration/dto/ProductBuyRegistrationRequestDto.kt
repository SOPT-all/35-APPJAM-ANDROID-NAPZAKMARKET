package com.napzak.market.data.registration.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductBuyRegistrationRequestDto(
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
    @SerialName("isPriceNegotiable")
    val isPriceNegotiable: Boolean,
)
