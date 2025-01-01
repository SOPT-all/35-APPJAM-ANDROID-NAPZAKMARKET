package com.napzak.market.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import com.napzak.market.core.designsystem.theme.NapzakMarketTheme
import com.napzak.market.presentation.dummy.DummyRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NapzakMarketTheme {
                DummyRoute(
                    modifier = Modifier.systemBarsPadding()
                )
            }
        }
    }
}