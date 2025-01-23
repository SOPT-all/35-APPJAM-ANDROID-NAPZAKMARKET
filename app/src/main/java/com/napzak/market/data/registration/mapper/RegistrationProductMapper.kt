package com.napzak.market.data.registration.mapper

import com.napzak.market.core.type.ProductConditionType.Companion.fromCondition
import com.napzak.market.data.registration.dto.ProductBuyRegistrationRequestDto
import com.napzak.market.data.registration.dto.ProductPhotoDto
import com.napzak.market.data.registration.dto.ProductSellRegistrationRequestDto
import com.napzak.market.domain.registration.model.BuyProduct
import com.napzak.market.domain.registration.model.ProductImage
import com.napzak.market.domain.registration.model.SellProduct

fun ProductImage.toProductPhotoDto() = ProductPhotoDto(
    photoUrl = imageUrl,
    sequence = sequence,
)

fun BuyProduct.toBuyRegistrationRequestDto() = ProductBuyRegistrationRequestDto(
    productPhotoDto = imageUrls.map { it.toProductPhotoDto() },
    genreId = genreId,
    title = title,
    description = description,
    price = price,
    isPriceNegotiable = isPriceNegotiable,
)

fun SellProduct.toSellRegistrationRequestDto() = ProductSellRegistrationRequestDto(
    productPhotoDto = imageUrls.map { it.toProductPhotoDto() },
    genreId = genreId,
    title = title,
    description = description,
    price = price,
    productCondition = fromCondition(productCondition),
    isDeliveryIncluded = isDeliveryIncluded,
    standardDeliveryFee = standardDeliveryFee,
    halfDeliveryFee = halfDeliveryFee,
)
