package com.napzak.market.presentation.mypage.type

import com.napzak.market.R

enum class MyPageMenuType(
    val titleResId: Int,
    val iconResId: Int
) {
    HISTORY(R.string.menu_history, R.drawable.ic_my_history_54),
    GENRE(R.string.menu_genre, R.drawable.ic_my_genre_54),
    FAVORITE(R.string.menu_favorite, R.drawable.ic_my_favorite_54),
    RECENT(R.string.menu_recent, R.drawable.ic_my_recent_54);
}