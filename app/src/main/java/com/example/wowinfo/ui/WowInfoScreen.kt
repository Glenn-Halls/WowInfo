package com.example.wowinfo.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.wowinfo.R
import com.example.wowinfo.model.Faction
import com.example.wowinfo.model.Layout
import com.example.wowinfo.model.Race
import com.example.wowinfo.ui.theme.Shapes
import com.example.wowinfo.ui.util.WowInfoNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WowInfoScreen(
    windowSize: WindowSizeClass,
    uiState: WowInfoUiState,
    viewModel: WowInfoViewModel,
    onTabPressed: (Faction) -> Unit,
    onListClick: (Race) -> Unit,
    modifier: Modifier = Modifier
) {
    // Get list of factions for navigation component
    val factionNavigationItems = viewModel.factionList
    // Get window width from window size
    val windowWidth = windowSize.widthSizeClass
    // Get window height from window size
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
    // Define layout based on nav type and race selection
    val layout = if (navigationType == WowInfoNavigationType.BOTTOM_BAR) {
        if (uiState.isShowingDetail) {
            Layout.Detail
        } else {
            Layout.List
        }
    } else Layout.ListAndDetail

    // Back button will reset race if race selected, close app if no race selected
    if (uiState.currentRace != null) {
        BackHandler {
            viewModel.resetRace()
        }
    }
    Scaffold(
        topBar = {
            if (windowHeight != WindowHeightSizeClass.Compact) {
                WowInfoTopBar(
                    onUpButtonClick = { viewModel.resetRace() }
                )
            }
        },
        bottomBar = {
            if (navigationType == WowInfoNavigationType.BOTTOM_BAR) {
                WowInfoBottomBar(
                    currentTab = uiState.currentFaction,
                    onTabPressed = onTabPressed,
                    bottomBarContent = factionNavigationItems,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                )
            }
        }
    ) { innerPadding ->
        Row(modifier = Modifier.padding(innerPadding)){
            if (windowWidth != WindowWidthSizeClass.Compact) {
                WowInfoNavigationRail(
                    currentTab = uiState.currentFaction,
                    onTabPressed = onTabPressed,
                    navRailContent = factionNavigationItems
                )
            }
            if (layout != Layout.Detail) {
                WowInfoRaceList(
                    raceList = uiState.raceList,
                    selectedRace = uiState.currentRace,
                    layout = layout,
                    onItemClick = onListClick,
                )
            }
            if (uiState.currentRace != null) {
                WowInfoRaceDetail(
                    race = uiState.currentRace,
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_medium))
                        .weight(1f)
                        .fillMaxHeight()
                )
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
                colors = NavigationRailItemDefaults.colors(
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
private fun WowInfoBottomBar(
    currentTab: Faction,
    onTabPressed: (Faction) -> Unit,
    bottomBarContent: List<Faction>,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        bottomBarContent.forEach {
            NavigationBarItem(
                selected = currentTab == it,
                onClick = { onTabPressed(it) },
                icon = {
                    Icon(
                        painter = painterResource(id = it.logo),
                        contentDescription = stringResource(id = it.name),
                        modifier = Modifier.size(50.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .weight(1f)
                    .background(it.color ?: MaterialTheme.colorScheme.primary)
                )
        }
    }
}

@Composable
private fun WowInfoRaceList(
    raceList: List<Race>,
    selectedRace: Race?,
    layout: Layout,
    onItemClick: (Race) -> Unit,
    modifier: Modifier = Modifier,
) {
    val screenWidth = if (layout == Layout.ListAndDetail) 0.5f else 1f
    val defaultPadding = dimensionResource(id = R.dimen.padding_medium)
    val rightPadding = if (layout == Layout.ListAndDetail) 0.dp else defaultPadding
    LazyColumn(
        contentPadding = PaddingValues(
            start = defaultPadding,
            top = defaultPadding,
            end = rightPadding,
            bottom = defaultPadding
        ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.fillMaxWidth(screenWidth)
    ) {
        items(raceList) {
            WowInfoRaceCard(
                race = it,
                isRaceSelected = it == selectedRace,
                onItemClick = onItemClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WowInfoRaceCard(
    race: Race,
    isRaceSelected: Boolean,
    onItemClick: (Race) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        onClick = { onItemClick(race) },
        colors = if (isRaceSelected) {
            CardDefaults.cardColors(
                containerColor = race.faction.color ?: MaterialTheme.colorScheme.primary,
            )
        } else CardDefaults.cardColors(),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.card_height))
                .padding(4.dp)
        ) {
            WowInfoRaceCrest(
                race = race,
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
            )
            Text(
                text = stringResource(id = race.name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium,
            )
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
        }
    }
}

@Composable
private fun WowInfoRaceCrest(race: Race, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(dimensionResource(R.dimen.race_crest_size))) {
        Image(
            painter = painterResource(race.crest),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit,
        )
    }
}

@Composable
private fun WowInfoRaceDetail(
    race: Race,
    modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(),
        shape = Shapes.large,
        modifier = modifier
    ) {
        Column(modifier = Modifier
            .padding(0.dp)
            .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(
                    id = race.wallpaper),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(race.description),
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )
        }
    }

}
