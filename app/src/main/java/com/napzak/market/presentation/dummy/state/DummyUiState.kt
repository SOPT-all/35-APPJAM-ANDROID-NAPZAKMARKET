package com.napzak.market.presentation.dummy.state

import com.napzak.market.core.common.state.UiState
import com.napzak.market.domain.dummy.model.DummyUser

data class DummyUiState(
    val dummyUsersLoadState: UiState<List<DummyUser>> = UiState.Empty,
)
