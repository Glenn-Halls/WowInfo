package com.example.wowinfo.ui

import RaceList
import com.example.wowinfo.R
import com.example.wowinfo.model.Faction
import com.example.wowinfo.model.Race

data class WowInfoUiState(
    val currentFaction: Faction = Faction(
        name = R.string.error,
        color = null,
        logo = R.drawable.ic_launcher_background,
    ),
    val raceList: List<Race> = RaceList.neutralRaces,
    val currentRace: Race?,
    val isShowingDetail: Boolean = false,
)
