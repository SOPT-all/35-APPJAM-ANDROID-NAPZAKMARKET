package com.napzak.market.data.detailpage.mapper

import com.napzak.market.data.detailpage.dto.ProductDetailResponse
import com.napzak.market.domain.detailpage.model.ProductDetail

fun ProductDetailResponse.toDomain(): ProductDetail = ProductDetail(
    isInterested = isInterested,
    detail = productDetail.toDomain(),
    photos = productPhotoList.map { it.toDomain() },
    store = storeInfo.toDomain(),
    reviews = storeReviewList.map { it.toDomain() },
)

fun ProductDetailResponse.ProductDetail.toDomain(): ProductDetail.Detail = ProductDetail.Detail(
    productId = productId,
    tradeType = tradeType,
    genreName = genreName,
    productName = productName,
    price = price,
    uploadTime = uploadTime,
    viewCount = viewCount,
    interestCount = interestCount,
    description = description,
    productCondition = productCondition ?: "",
    standardDeliveryFee = standardDeliveryFee,
    halfDeliveryFee = halfDeliveryFee,
    isDeliveryIncluded = isDeliveryIncluded,
    isPriceNegotiable = isPriceNegotiable,
    tradeStatus = tradeStatus,
    isOwnedByCurrentUser = isOwnedByCurrentUser,
)

fun ProductDetailResponse.ProductPhoto.toDomain(): ProductDetail.Photo = ProductDetail.Photo(
    photoId = photoId,
    photoUrl = photoUrl,
    photoSequence = photoSequence,
)

fun ProductDetailResponse.StoreInfo.toDomain(): ProductDetail.Store = ProductDetail.Store(
    userId = userId,
    storePhoto = storePhoto,
    nickname = nickname,
    totalProducts = totalProducts,
    totalTransactions = totalTransactions,
)

fun ProductDetailResponse.StoreReview.toDomain(): ProductDetail.Review = ProductDetail.Review(
    reviewId = reviewId,
    reviewerNickname = reviewerNickname,
    rating = rating,
    comment = comment,
    relatedProductId = relatedProductId,
    relatedProductName = relatedProductName,
)
