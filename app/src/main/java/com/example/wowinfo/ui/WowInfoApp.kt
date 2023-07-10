package com.example.wowinfo.ui

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wowinfo.model.Faction
import com.example.wowinfo.ui.util.WowInfoNavigationType


@Composable
fun WowInfoApp(
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier
) {
    // Create an instance of WowInfoViewModel
    val viewModel: WowInfoViewModel = viewModel()
    // Create an observable reference to uiState
    val uiState by viewModel.uiState.collectAsState()

    // Window height determines the layout of some Composables
    val windowHeight = windowSize.heightSizeClass

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

    WowInfoScreen(
        windowHeight = windowHeight,
        navigationType = navigationType,
        uiState = uiState,
        viewModel = viewModel,
        onTabPressed = { clickedFaction: Faction ->
            viewModel.updateCurrentFaction(newFaction = clickedFaction)
        }
    )
}
