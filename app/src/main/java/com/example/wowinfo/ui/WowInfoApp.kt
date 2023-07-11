package com.example.wowinfo.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun WowInfoApp(
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier
) {
    // Create an instance of WowInfoViewModel
    val viewModel: WowInfoViewModel = viewModel()
    // Create an observable reference to uiState
    val uiState by viewModel.uiState.collectAsState()

    WowInfoScreen(
        windowSize = windowSize,
        uiState = uiState,
        viewModel = viewModel,
        onTabPressed = { viewModel.updateCurrentFaction(it) },
        onListClick = { viewModel.updateCurrentRace(it) },
    )
}
