package com.napzak.market.presentation.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _isStartButtonVisible = MutableStateFlow(false)
    val isStartButtonVisible = _isStartButtonVisible.asStateFlow()

    fun setIsStartButtonVisible(isVisible: Boolean) {
        _isStartButtonVisible.value = isVisible
    }
}