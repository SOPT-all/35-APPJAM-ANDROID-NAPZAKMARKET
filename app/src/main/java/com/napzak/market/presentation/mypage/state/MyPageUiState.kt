package com.napzak.market.presentation.mypage.state

data class MyPageUiState(
    val storeId: Long = 0L,
    val profileImageUrl: String? = null,
    val nickname: String = "",
)
