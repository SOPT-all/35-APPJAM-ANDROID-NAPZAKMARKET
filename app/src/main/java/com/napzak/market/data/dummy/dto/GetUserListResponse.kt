package com.napzak.market.data.dummy.dto

import kotlinx.serialization.SerialName

data class GetUserListResponse(
    @SerialName("page")
    val page: Int?,
    @SerialName("per_page")
    val perPage: Int?,
    @SerialName("total")
    val total: Int?,
    @SerialName("total_pages")
    val totalPage: Int?,
    @SerialName("data")
    val data: List<UserData>?,
    @SerialName("support")
    val support: Support?
) {
    data class UserData(
        @SerialName("id")
        val id: Int?,
        @SerialName("email")
        val email: String?,
        @SerialName("first_name")
        val firstName: String?,
        @SerialName("last_name")
        val lastName: String?,
        @SerialName("avatar")
        val profileImage: String?,
    )
    data class Support(
        @SerialName("url")
        val url: String?,
        @SerialName("text")
        val text: String?,
    )
}