package com.example.wowinfo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults.colors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.wowinfo.R
import com.example.wowinfo.model.Faction
import com.example.wowinfo.model.Race
import com.example.wowinfo.ui.util.WowInfoNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WowInfoScreen(
    windowHeight: WindowHeightSizeClass,
    navigationType: WowInfoNavigationType,
    uiState: WowInfoUiState,
    viewModel: WowInfoViewModel,
    onTabPressed: (Faction) -> Unit,
    modifier: Modifier = Modifier
) {
    // Get list of factions for navigation component
    val factionNavigationItems = viewModel.factionList

    Scaffold(
        topBar = {
            if (windowHeight != WindowHeightSizeClass.Compact) {
                WowInfoTopBar(
                    onUpButtonClick = { /*TODO*/}
                )
            }
        }
    ) { innerPadding ->
        Row(modifier = Modifier.padding(innerPadding)){
            WowInfoNavigationRail(
                currentTab = uiState.currentFaction,
                onTabPressed = onTabPressed,
                navRailContent = factionNavigationItems
            )
            // Placeholder: showing height class and nav type
            Column(modifier = Modifier.padding(horizontal = 4.dp)){
                Text(
                    text = "Height Class: $windowHeight"
                )
                Text(
                    text = "Navigation Type: $navigationType"
                )
                Text(
                    text = "Faction Selected: ${
                        stringResource(uiState.currentFaction.name)
                    }"
                )
                WowInfoRaceList(raceList = uiState.raceList)
            }
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

@Composable
private fun WowInfoNavigationRail(
    currentTab: Faction,
    onTabPressed: (Faction) -> Unit,
    navRailContent: List<Faction>,
    modifier: Modifier = Modifier
) {
    NavigationRail(modifier = modifier) {
        for (navItem in navRailContent) {
            NavigationRailItem(
                selected = currentTab == navItem,
                onClick = { onTabPressed(navItem) },
                icon = {
                    Icon(
                        painter = painterResource(id = navItem.logo),
                        contentDescription = stringResource(navItem.name),
                        modifier = Modifier.size(50.dp)
                    )
                },
                colors = colors(
                    indicatorColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .weight(1f)
                    .background(navItem.color ?: MaterialTheme.colorScheme.primary)
            )
        }
    }
}

@Composable
private fun WowInfoRaceList(
    raceList: List<Race>
) {
    Column {
        for (race in raceList) {
            Text(text = stringResource(id = race.name))
        }
    }
}
