package com.napzak.market.presentation.main.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.napzak.market.R

enum class RegisterTab(
    @DrawableRes val iconRes: Int,
    @StringRes val titleRes: Int,
) {
    SELL(
        iconRes = R.drawable.ic_sell_24,
        titleRes = R.string.main_bottom_bar_register_sell
    ),
    BUY(
        iconRes = R.drawable.ic_buy_28,
        titleRes = R.string.main_bottom_bar_register_buy
    );
}