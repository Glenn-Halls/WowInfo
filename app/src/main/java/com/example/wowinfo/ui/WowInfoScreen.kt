package com.example.wowinfo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.wowinfo.R
import com.example.wowinfo.ui.util.WowInfoNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WowInfoScreen(
    windowHeight: WindowHeightSizeClass,
    uiState: WowInfoUiState,
    navigationType: WowInfoNavigationType,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            if (windowHeight != WindowHeightSizeClass.Compact) {
                WowInfoTopBar(
                    onUpButtonClick = { /*TODO*/}
                )
            }
        }
    ) { innerPadding ->
        // Placeholder: showing height class and nav type
        Column(modifier = Modifier.padding(innerPadding)){
            Text(
                text = "Height Class: $windowHeight"
            )
            Text(
                text = "Navigation Type: $navigationType"
            )
            Text(
                text = "Faction Selected: ${uiState.currentFaction}"
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WowInfoTopBar(onUpButtonClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "WoW Character Info",
                style = MaterialTheme.typography.displayLarge
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            IconButton(onClick = onUpButtonClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        }
    )
}
