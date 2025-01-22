package com.napzak.market.presentation.detailpage

sealed interface DetailPageSideEffect {
    data object ShowLikeSnackBar : DetailPageSideEffect
    data object DismissLikeSnackBar : DetailPageSideEffect
}