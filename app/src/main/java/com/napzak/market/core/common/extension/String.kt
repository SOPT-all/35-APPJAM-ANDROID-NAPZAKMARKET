package com.napzak.market.core.common.extension

import java.text.DecimalFormat

fun String.formatToPriceString(): String = toLongOrNull()?.let {
    DecimalFormat("#,###").format(it)
} ?: this
