package com.napzak.market.presentation.onboarding

sealed interface OnboardingSideEffect {
    data object NavigateToHome : OnboardingSideEffect
}