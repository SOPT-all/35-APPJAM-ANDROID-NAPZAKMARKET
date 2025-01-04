package com.napzak.market.data.dummy.mapper

import com.napzak.market.data.dummy.dto.GetUserListResponse
import com.napzak.market.domain.dummy.model.DummyUser

fun GetUserListResponse.UserData.toDummyUser() = DummyUser(
    id = this.id ?: 0,
    email = this.email.orEmpty(),
    firstName = this.firstName.orEmpty(),
    lastName = this.lastName.orEmpty(),
    profileImage = this.profileImage.orEmpty(),
)