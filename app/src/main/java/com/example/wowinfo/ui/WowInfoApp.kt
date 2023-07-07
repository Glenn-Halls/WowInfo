package com.example.wowinfo.ui

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.wowinfo.ui.util.WowInfoNavigationType

@Composable
fun WowInfoApp(
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier
) {
    // Create an instance of WowInfoViewModel
    val viewModel = WowInfoViewModel()
    // Create an observable reference to uiState
    val uiState by viewModel.uiState.collectAsState()

    // Define navigation type based on WindowSize dimensions
    val navigationType: WowInfoNavigationType =
        if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact) {
            WowInfoNavigationType.BOTTOM_BAR
        } else when (windowSize.heightSizeClass) {
            WindowHeightSizeClass.Compact,
            WindowHeightSizeClass.Expanded -> WowInfoNavigationType.NAVIGATION_RAIL
            WindowHeightSizeClass.Medium -> WowInfoNavigationType.NAVIGATION_DRAWER
            else -> WowInfoNavigationType.BOTTOM_BAR
        }

    val windowHeight = windowSize.heightSizeClass

    WowInfoScreen(windowHeight = windowHeight, uiState = uiState, navigationType = navigationType)
}
