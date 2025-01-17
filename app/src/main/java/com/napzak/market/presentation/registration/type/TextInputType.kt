package com.napzak.market.presentation.registration.type

sealed interface TextInputType

sealed class PlainTextInputType : TextInputType {
    data object Title : PlainTextInputType()
    data object Description : PlainTextInputType()
}

sealed class NumeralInputType: TextInputType {
    data object ProductSalePrice : NumeralInputType()
    data object ProductPurchasePrice : NumeralInputType()
    data object NormalPostFee : NumeralInputType()
    data object HalfPostFee : NumeralInputType()
}
