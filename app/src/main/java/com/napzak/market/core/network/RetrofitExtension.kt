package com.napzak.market.core.network

fun String?.isJsonObject(): Boolean = this?.startsWith("{") == true && this.endsWith("}")

fun String?.isJsonArray(): Boolean = this?.startsWith("[") == true && this.endsWith("]")